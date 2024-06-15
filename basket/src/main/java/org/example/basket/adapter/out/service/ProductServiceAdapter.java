package org.example.basket.adapter.out.service;

import lombok.RequiredArgsConstructor;
import org.example.basket.application.port.out.GetProductPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductServiceAdapter implements GetProductPort {

    private final ProductFeignClient productFeignClient;

    @Override
    public boolean getProduct(long sizeId) {
        boolean isExistProduct = productFeignClient.existProductBySizeId(sizeId);
        return isExistProduct;
    }

    @Override
    public List<Product> getProductListByProductIds(List<Long> productIds) {
        List<Product> findProductList = productFeignClient.findByProductIds(productIds);
        return findProductList;
    }
}
