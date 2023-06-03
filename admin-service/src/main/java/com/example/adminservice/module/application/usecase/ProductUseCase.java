package com.example.adminservice.module.application.usecase;

import com.example.adminservice.module.domain.product.dto.ProductDto;
import com.example.adminservice.module.domain.product.entity.Product;
import com.example.adminservice.module.domain.product.service.ProductReadService;
import com.example.adminservice.module.domain.product.service.ProductWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductUseCase {

    private final ProductWriteService productWriteService;
    private final ProductReadService productReadService;

    public ProductDto createProductExecute(ProductDto productDto) {
        Product product = productWriteService.createProduct(productDto);
        ProductDto toProductDto = productReadService.toProductDto(product);

        return toProductDto;
    }

    public ProductDto readProduct(long productId) throws IllegalAccessException {
        ProductDto productDto = null;

        try {
            Product product = productReadService.readProduct(productId);
            productDto = productReadService.toProductDto(product);
        }catch (Exception e){
            log.error("상품을 불러오지 못했습니다. readProduct()");
        }

        return productDto;
    }

    public ProductDto updateProduct(long productId, ProductDto updateProductDto){
        ProductDto productDto = null;

        try{
            Product product = productReadService.readProduct(productId);
            product = productWriteService.updateProduct(product,updateProductDto);
            productDto = productReadService.toProductDto(product);
        }catch (Exception e){

        }

        return productDto;
    }
}
