package com.example.adminservice.application.service;

import com.example.adminservice.adapter.out.persistence.product.ProductMapper;
import com.example.adminservice.adapter.out.persistence.product.entity.ProductEntity;
import com.example.adminservice.application.port.in.FindProductUseCase;
import com.example.adminservice.application.port.in.product.FindProductCommand;
import com.example.adminservice.application.port.out.FindProductPort;
import com.example.adminservice.common.UseCase;
import com.example.adminservice.domain.product.dto.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@UseCase
@Slf4j
public class FindProductService implements FindProductUseCase {
    private final FindProductPort findProductPort;
    private final ProductMapper productMapper;
    @Override
    public ProductVo findProduct(FindProductCommand command) {
        ProductVo.ProductId productId = new ProductVo.ProductId(command.getProductId());
        ProductEntity findProduct =  findProductPort.findProduct(productId);
        return productMapper.mapToDomainEntity(findProduct);
    }
}
