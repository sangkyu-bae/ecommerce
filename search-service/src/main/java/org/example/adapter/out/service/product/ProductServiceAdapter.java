package org.example.adapter.out.service.product;

import lombok.RequiredArgsConstructor;
import org.example.application.port.out.GetProductPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductServiceAdapter implements GetProductPort {

    private final ProductFeignClient productFeignClient;
    @Override
    public List<String> getProductBrandName(List<Long> productIds) {
        return productFeignClient.findByProductIds(productIds)
                .stream().map(product->product.getBrand().getName())
                .collect(Collectors.toList());
    }
}
