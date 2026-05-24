package com.nkh.orderservice.dto.response;

import com.nkh.orderservice.entity.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
public class OrderRes implements Serializable {
    private String status;
    private BigDecimal totalAmount;
    private List<OrderItemRes> orderItems;
}
