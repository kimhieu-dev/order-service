package com.nkh.orderservice.dto.request;

import com.nkh.orderservice.client.ProductClient;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRes implements Serializable {
    private String id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private String categoryId;
}
