package com.example.adminservice.application.service;

import com.example.adminservice.adapter.out.persistence.product.*;
import com.example.adminservice.adapter.out.persistence.product.entity.ProductEntity;
import com.example.adminservice.application.port.in.product.*;
import com.example.adminservice.application.port.in.RegisterProductUseCase;
import com.example.adminservice.application.port.out.RegisterProductPort;

import com.example.adminservice.domain.productentity.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;

import java.util.Set;

@RequiredArgsConstructor
@UseCase
@Slf4j
public class RegisterProductService implements RegisterProductUseCase {

    private final RegisterProductPort registerProductPort;
    private final ProductMapper productMapper;
    @Override
    public ProductVo registerProduct(RegisterProductCommand command) {

        Set<ProductVo.ProductComponentEntityVo> productComponentEntityVos = productMapper.mapToProductComponentEntityVo(command);
        ProductVo.ProductBrandVo brand = productMapper.mapToBrand(command.getBrand());
        ProductVo.ProductCategoryVo category =productMapper.mapToCategory(command.getCategory());

        ProductVo createProduct = ProductVo.createGenerateProductVo(
                new ProductVo.ProductId(0),
                new ProductVo.ProductName(command.getName()),
                new ProductVo.ProductPrice(command.getPrice()),
                new ProductVo.ProductDescription(command.getDescription()),
                new ProductVo.ProductImage(command.getProductImage()),
                brand, category,productComponentEntityVos
                );

       ProductEntity productEntity = registerProductPort.createProduct(createProduct);

        return productMapper.mapToDomainEntity(productEntity);
    }

}
