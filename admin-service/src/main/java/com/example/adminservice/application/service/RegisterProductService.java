package com.example.adminservice.application.service;

import com.example.adminservice.adapter.axon.command.ProductCreateCommand;
import com.example.adminservice.adapter.out.persistence.product.*;
import com.example.adminservice.adapter.out.persistence.product.entity.ProductEntity;
import com.example.adminservice.application.port.in.command.RegisterProductCommand;
import com.example.adminservice.application.port.in.usecase.RegisterProductUseCase;
import com.example.adminservice.application.port.out.RegisterProductPort;

import com.example.adminservice.application.port.out.SendCreateProductTaskPort;
import com.example.adminservice.domain.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.example.UseCase;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@UseCase
@Slf4j
public class RegisterProductService implements RegisterProductUseCase {

    private final RegisterProductPort registerProductPort;
    private final ProductMapper productMapper;

    private final CommandGateway commandGateway;

    private final SendCreateProductTaskPort sendCreateProductTaskPort;
    @Override
    public ProductVo registerProduct(RegisterProductCommand command) {

        Set<ProductVo.ProductComponentEntityVo> productComponentEntityVos = productMapper.mapToProductComponentEntityVo(command);
        ProductVo.ProductBrandVo brand = productMapper.mapToBrand(command.getBrand());
        ProductVo.ProductCategoryVo category =productMapper.mapToCategory(command.getCategory());

        String aggregate = UUID.randomUUID().toString();

        ProductVo createProduct = ProductVo.createGenerateProductVo(
                new ProductVo.ProductId(0),
                new ProductVo.ProductName(command.getName()),
                new ProductVo.ProductPrice(command.getPrice()),
                new ProductVo.ProductDescription(command.getDescription()),
                new ProductVo.ProductImage(command.getProductImage()),
                new ProductVo.ProductAggregateIdentifier(aggregate),
                brand, category,productComponentEntityVos
                );


        ProductEntity productEntity = registerProductPort.createProduct(createProduct);

        ProductCreateCommand axonCommand = ProductCreateCommand.builder()
                .productImage(command.getProductImage())
                .price(command.getPrice())
                .description(command.getDescription())
                .name(command.getName())
                .aggregateIdentifier(aggregate)
                .build();


        commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
            if (throwable != null) {
               log.error("throwable = " + throwable);
                throw new RuntimeException(throwable);
            } else{
                System.out.println("result = " + result);
            }
        });

        sendCreateProductTaskPort.sendCreateProductTask(productEntity.getId());
       return productMapper.mapToDomainEntity(productEntity);
    }

}
