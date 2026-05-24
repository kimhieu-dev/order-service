package com.nkh.orderservice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CreateOrderReq implements Serializable {
    @NotEmpty
    private String userId;

    @NotEmpty
    @Valid
    private List<CreateOrderItemReq> orderItems;
}
