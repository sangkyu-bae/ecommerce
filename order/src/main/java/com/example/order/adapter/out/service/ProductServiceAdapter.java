package com.example.order.adapter.out.service;

import com.example.order.application.port.out.GetProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductServiceAdapter implements GetProductPort {

    private final ProductFeignClient productFeignClient;
    @Override
    public Product getProduct(long productId) {
        Product findProduct = productFeignClient.getProduct(productId);
        return findProduct;
    }
}
