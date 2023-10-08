package com.example.adminservice.application.service;

import com.example.adminservice.adapter.out.persistence.product.ProductMapper;
import com.example.adminservice.adapter.out.persistence.product.entity.ProductEntity;
import com.example.adminservice.application.port.in.UpdateProductUseCase;
import com.example.adminservice.application.port.in.product.UpdateProductCommand;
import com.example.adminservice.application.port.in.product.UpdateProductQuantityCommand;
import com.example.adminservice.application.port.out.FindProductPort;
import com.example.adminservice.application.port.out.UpdateProductPort;
import com.example.adminservice.common.UseCase;
import com.example.adminservice.domain.productentity.ProductVo;
import com.example.adminservice.module.common.error.ErrorException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@RequiredArgsConstructor
@UseCase
@Slf4j
public class UpdateProductService implements UpdateProductUseCase {

    private final UpdateProductPort updateProductPort;
    private final ProductMapper productMapper;

    private final FindProductPort findProductPort;
    @Override
    public ProductVo updateProduct(UpdateProductCommand command) {
        Set<ProductVo.ProductComponentEntityVo> productComponentEntityVos = productMapper.mapToProductComponentEntityVo(command);
        ProductVo.ProductBrandVo brand = productMapper.mapToBrand(command.getBrand());
        ProductVo.ProductCategoryVo category =productMapper.mapToCategory(command.getCategory());
        ProductVo updateProductVO = ProductVo.createGenerateProductVo(
                new ProductVo.ProductId(command.getId()),
                new ProductVo.ProductName(command.getName()),
                new ProductVo.ProductPrice(command.getPrice()),
                new ProductVo.ProductDescription(command.getDescription()),
                new ProductVo.ProductImage(command.getProductImage()),
                brand, category,productComponentEntityVos
        );

        ProductEntity updateProductEntity = updateProductPort.updateProduct(updateProductVO);
        return productMapper.mapToDomainEntity(updateProductEntity);
    }

    @Override
    public boolean updateProductQuantity(UpdateProductQuantityCommand command) {
        try{
            ProductVo.ProductId productId = new ProductVo.ProductId(command.getProductId());
            ProductEntity productEntity = findProductPort.findProduct(productId);

            productEntity.updateProductQuantity(
                    command.getColorId(),
                    command.getAmount(),
                    command.getSize()
            );

            ProductVo updateProductVO = productMapper.mapToDomainEntity(productEntity);
            updateProductPort.updateProduct(updateProductVO);
        }catch (ErrorException e){
            return false;
        }

        return true;
    }
}
