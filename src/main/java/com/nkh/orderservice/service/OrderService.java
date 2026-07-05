package com.nkh.orderservice.service;

import com.nkh.orderservice.dto.request.CreateOrderReq;
import com.nkh.orderservice.dto.response.OrderRes;
import com.nkh.orderservice.entity.Order;

public interface OrderService {
    Order create(CreateOrderReq request);
}
