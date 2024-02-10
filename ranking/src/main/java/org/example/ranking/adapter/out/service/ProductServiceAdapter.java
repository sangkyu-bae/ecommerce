package org.example.ranking.adapter.out.service;

import lombok.RequiredArgsConstructor;
import org.example.ranking.application.port.out.GetProductPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductServiceAdapter implements GetProductPort {

    private final ProductFeignClient productFeignClient;
    @Override
    public List<Product> getProductAll() {
        return productFeignClient.getProductAll();
    }
}
