package com.nkh.orderservice.controller;

import com.nkh.orderservice.dto.BaseResponse;
import com.nkh.orderservice.dto.request.CreateOrderReq;
import com.nkh.orderservice.dto.response.OrderRes;
import com.nkh.orderservice.entity.Order;
import com.nkh.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Validated
public class OrderController {
    private final OrderService orderService;

//    StringSerializer
//    JsonSerializer
    @PostMapping
    public BaseResponse<Order> create(@RequestBody @Valid CreateOrderReq request) {
        Order response = orderService.create(request);
        return BaseResponse.success(response);
    }
}
