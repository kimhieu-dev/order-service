package com.nkh.orderservice.mapper;

import com.nkh.orderservice.dto.response.OrderItemRes;
import com.nkh.orderservice.entity.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    List<OrderItemRes> toListOrderItemRes(List<OrderItem> orderItems);
}
