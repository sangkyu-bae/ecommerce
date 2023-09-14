package com.example.adminservice.application.service;

import com.example.adminservice.adapter.out.persistence.ProductEntity;
import com.example.adminservice.application.port.in.RegisterProductCommand;
import com.example.adminservice.application.port.in.RegisterProductUseCase;
import com.example.adminservice.application.port.out.RegisterProductPort;
import com.example.adminservice.domain.product.dto.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class RegisterProductService implements RegisterProductUseCase {

    private final RegisterProductPort registerProductPort;

    private final ModelMapper modelMapper;
    @Override
    public ProductVo registerProduct(RegisterProductCommand command) {

       ProductEntity productEntity = registerProductPort.createProduct(
                new ProductVo.ProductName(command.getName()),
                new ProductVo.ProductPrice(command.getPrice()),
                new ProductVo.ProductDescription(command.getDescription()),
                new ProductVo.ProductImage(command.getProductImage()),
                command.getBrand(),
                command.getCategory(),
                command.getProductComponents()
        );

        return modelMapper.map(productEntity,ProductVo.class);
    }
}
