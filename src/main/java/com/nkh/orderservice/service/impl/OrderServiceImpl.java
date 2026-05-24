package com.nkh.orderservice.service.impl;

import com.nkh.orderservice.client.ProductClient;
import com.nkh.orderservice.dto.client.request.ProductFilter;
import com.nkh.orderservice.dto.request.CreateOrderItemReq;
import com.nkh.orderservice.dto.request.CreateOrderReq;
import com.nkh.orderservice.dto.request.ProductRes;
import com.nkh.orderservice.dto.response.OrderItemRes;
import com.nkh.orderservice.dto.response.OrderRes;
import com.nkh.orderservice.entity.Order;
import com.nkh.orderservice.entity.OrderItem;
import com.nkh.orderservice.exception.AppException;
import com.nkh.orderservice.exception.ErrorCode;
import com.nkh.orderservice.mapper.OrderItemMapper;
import com.nkh.orderservice.repository.OrderItemRepo;
import com.nkh.orderservice.repository.OrderRepo;
import com.nkh.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final ProductClient productClient;
    private final OrderItemRepo orderItemRepo;
    private final OrderItemMapper orderItemMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderRes create(CreateOrderReq request) {
        List<String> productIds = request.getOrderItems().stream()
                .map(CreateOrderItemReq::getProductId).distinct().toList();

        List<ProductRes> products = productClient.getProductsByIds(new ProductFilter(productIds));

        Map<String,ProductRes> productPriceMap = new HashMap<>();
        products.forEach(product -> {
            productPriceMap.put(product.getId(),product);
        });

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setStatus("NEW");
        order.setTotalAmount(BigDecimal.ZERO);
        Order savedOrder = orderRepo.save(order);

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CreateOrderItemReq orderItemReq: request.getOrderItems() ){
            ProductRes productRes = productPriceMap.get(orderItemReq.getProductId());
            if(productRes == null){
                throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
            }

            if (orderItemReq.getQuantity() > productRes.getStock()){
                throw new AppException(ErrorCode.PRODUCT_NOT_ENOUGH);
            }

            BigDecimal productPrice = productRes.getPrice();
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(savedOrder.getId());
            orderItem.setProductId(orderItemReq.getProductId());

            orderItems.add(orderItem);
            totalAmount = totalAmount.add(productPrice.multiply(BigDecimal.valueOf(orderItemReq.getQuantity())));

        }
        orderItemRepo.saveAll(orderItems);
        savedOrder.setTotalAmount(totalAmount);

        orderRepo.save(savedOrder);

        List<OrderItemRes> orderItemResList = orderItemMapper.toListOrderItemRes(orderItems);

        return OrderRes.builder()
                .status(savedOrder.getStatus())
                .totalAmount(savedOrder.getTotalAmount())
                .orderItems(orderItemResList)
                .build();
    }
}
