package com.example.adminservice.domain.product.service;

import com.example.adminservice.domain.product.OrderDto;
import com.example.adminservice.domain.product.entity.ColorProduct;
import com.example.adminservice.domain.product.entity.Product;
import com.example.adminservice.domain.product.repository.ColorProductRepository;
import com.example.adminservice.module.common.error.ErrorException;
import com.example.adminservice.module.common.error.errorImpl.ColorProductErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class ColorProductReadService {
    private final ColorProductRepository colorProductRepository;

    public ColorProduct read(long colorProductId){
        return colorProductRepository.findById(colorProductId).
                orElseThrow(()->
                        new ErrorException(ColorProductErrorCode.COLOR_PRODUCT_NOT_FOUND,"read")
                );
    }

    public ColorProduct readByProduct(Product product, OrderDto orderDto){
        return product.colorProduct(orderDto);
    }
}
