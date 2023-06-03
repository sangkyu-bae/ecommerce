package com.example.adminservice.module.domain.product.service;

import com.example.adminservice.module.domain.product.dto.ProductDto;
import com.example.adminservice.module.domain.product.entity.Product;
import com.example.adminservice.module.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UnknownFormatConversionException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProductReadService {
    private final ProductRepository productRepository;

    public ProductDto toProductDto(Product product) {
        ProductDto productDto = null;
        try {
            productDto = new ProductDto(product);
        } catch (Exception exception) {
            log.error("ProductDto 변환 실패하였습니다. Error toProductDto()");
            throw new UnknownFormatConversionException("could Not Convert Member to toProductDto");
        }

        return productDto;
    }

    public Product readProduct(long productId) throws IllegalAccessException {
        Product product = null;
        try{
            product = productRepository.findById(productId).orElseThrow(() ->
                    new IllegalAccessException("존재하지 않은 상품입니다. readProduct()")
            );
        }catch (Exception e){
            log.error("존재하지 않은 상품입니다. readProduct()");
        }
        return product;
    }
}
