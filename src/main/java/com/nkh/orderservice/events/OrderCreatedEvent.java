package com.nkh.orderservice.events;

import com.nkh.orderservice.entity.Order;
import com.nkh.orderservice.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCreatedEvent extends Order {
    private List<OrderItem> orderItems;
}
