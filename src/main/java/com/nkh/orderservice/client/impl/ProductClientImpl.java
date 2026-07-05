package com.nkh.orderservice.client.impl;

import com.nkh.orderservice.client.ProductClient;
import com.nkh.orderservice.dto.BaseResponse;
import com.nkh.orderservice.dto.client.request.ProductFilter;
import com.nkh.orderservice.dto.request.ProductRes;
import com.nkh.orderservice.exception.AppException;
import com.nkh.orderservice.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductClientImpl implements ProductClient {
    private final WebClient.Builder webClientBuilder;

    @Override
    public List<ProductRes> getProductsByIds(ProductFilter productFilter) {
        BaseResponse<List<ProductRes>> response = webClientBuilder.build()
                .post()
                .uri("http://localhost:8888/api/v1/products/search")
                .bodyValue(productFilter)
                .retrieve()
                .bodyToMono(new  ParameterizedTypeReference<BaseResponse<List<ProductRes>>>(){})
                .block();
        if (response == null || response.getData() == null) {
            throw new AppException(ErrorCode.RESPONSE_NULL);
        }
        return response.getData();
    }
}
