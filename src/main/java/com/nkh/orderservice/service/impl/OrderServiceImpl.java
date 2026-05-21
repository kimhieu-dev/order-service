package com.nkh.orderservice.service.impl;

import com.nkh.orderservice.repository.OrderRepo;
import com.nkh.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
}
