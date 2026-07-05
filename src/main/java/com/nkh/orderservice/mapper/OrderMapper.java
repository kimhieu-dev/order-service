package com.nkh.orderservice.mapper;

import com.nkh.orderservice.entity.Order;
import com.nkh.orderservice.events.OrderCreatedEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderCreatedEvent toEvent(Order order);
}
