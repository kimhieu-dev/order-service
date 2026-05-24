package com.nkh.orderservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class OrderItemRes implements Serializable {
    private String orderId;
    private String productId;
    private BigDecimal price;
    private BigDecimal quantity;
}
