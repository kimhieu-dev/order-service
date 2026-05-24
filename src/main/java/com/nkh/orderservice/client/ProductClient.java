package com.nkh.orderservice.client;

import com.nkh.orderservice.dto.client.request.ProductFilter;
import com.nkh.orderservice.dto.request.ProductRes;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface ProductClient {
    List<ProductRes> getProductsByIds(ProductFilter productFilter);
}
