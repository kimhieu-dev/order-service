package com.nkh.orderservice.service;

import com.nkh.orderservice.dto.request.CreateOrderReq;
import com.nkh.orderservice.dto.response.OrderRes;

public interface OrderService {
    OrderRes create(CreateOrderReq request);
}
