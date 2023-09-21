package com.example.adminservice.application.service;

import com.example.adminservice.adapter.out.persistence.product.BrandEntity;
import com.example.adminservice.adapter.out.persistence.product.CategoryEntity;
import com.example.adminservice.adapter.out.persistence.product.ProductComponentEntity;
import com.example.adminservice.adapter.out.persistence.product.ProductEntity;
import com.example.adminservice.application.port.in.product.RegisterProductCommand;
import com.example.adminservice.application.port.in.RegisterProductUseCase;
import com.example.adminservice.application.port.out.RegisterProductPort;
import com.example.adminservice.common.UseCase;
import com.example.adminservice.domain.product.dto.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@UseCase
@Slf4j
public class RegisterProductService implements RegisterProductUseCase {

    private final RegisterProductPort registerProductPort;

    private final ModelMapper modelMapper;
    @Override
    public ProductVo registerProduct(RegisterProductCommand command) {

        BrandEntity brand = modelMapper.map(command.getBrand(),BrandEntity.class);
        CategoryEntity category = modelMapper.map(command.getCategory(),CategoryEntity.class);
        Set<ProductComponentEntity> componentEntities = command.getProductComponents()
                .stream().map(component -> modelMapper.map(component,ProductComponentEntity.class)).collect(Collectors.toSet());

        ProductVo createProduct = ProductVo.createGenerateProductVo(
                new ProductVo.ProductName(command.getName()),
                new ProductVo.ProductPrice(command.getPrice()),
                new ProductVo.ProductDescription(command.getDescription()),
                new ProductVo.ProductImage(command.getProductImage()),
                brand,category,componentEntities
                );

       ProductEntity productEntity = registerProductPort.createProduct(createProduct);

        return modelMapper.map(productEntity,ProductVo.class);
    }
}
