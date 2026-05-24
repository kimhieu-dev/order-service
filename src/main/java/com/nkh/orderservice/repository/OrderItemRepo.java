package com.nkh.orderservice.repository;

import com.nkh.orderservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem,String> {
}
