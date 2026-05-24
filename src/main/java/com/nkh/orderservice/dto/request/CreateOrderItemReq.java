package com.nkh.orderservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CreateOrderItemReq implements Serializable {
    @NotEmpty
    private String productId;
    @NotNull
    private int quantity;
}
