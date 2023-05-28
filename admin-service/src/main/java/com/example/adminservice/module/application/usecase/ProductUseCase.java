package com.example.adminservice.module.application.usecase;

import com.example.adminservice.module.domain.product.dto.ProductDto;
import com.example.adminservice.module.domain.product.entity.Product;
import com.example.adminservice.module.domain.product.repository.ProductRepository;
import com.example.adminservice.module.domain.product.service.ProductReadService;
import com.example.adminservice.module.domain.product.service.ProductWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductUseCase {

    private final ProductWriteService productWriteService;
    private final ProductReadService productReadService;
    private final ProductRepository productRepository;

    public ProductDto createMemberExecute(ProductDto productDto){
        Product product = productWriteService.createProduct(productDto);
        ProductDto toProductDto = productReadService.toProductDto(product);

        return toProductDto;
    }
}
