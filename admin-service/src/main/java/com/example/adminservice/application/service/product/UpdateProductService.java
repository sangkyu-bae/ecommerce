package com.example.adminservice.application.service.product;

import com.example.adminservice.adapter.out.persistence.entity.SizeEntity;
import com.example.adminservice.adapter.out.persistence.product.ProductMapper;
import com.example.adminservice.adapter.out.persistence.entity.ProductEntity;
import com.example.adminservice.adapter.out.persistence.product.SizePersistenceAdapter;
import com.example.adminservice.application.port.in.usecase.product.UpdateProductUseCase;
import com.example.adminservice.application.port.in.command.OrderToUpdateProductCommand;
import com.example.adminservice.application.port.in.command.UpdateProductCommand;
import com.example.adminservice.application.port.in.command.UpdateProductQuantityCommand;
import com.example.adminservice.application.port.out.product.FindProductPort;
import com.example.adminservice.application.port.out.brand.RequestDeliveryPort;
import com.example.adminservice.application.port.out.brand.UpdateProductPort;
import com.example.adminservice.application.port.out.brand.UpdateProductSizePort;
import com.example.adminservice.domain.ProductVo;
import com.example.adminservice.domain.SizeVo;
import com.example.adminservice.infra.error.ErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@UseCase
@Slf4j
public class UpdateProductService implements UpdateProductUseCase {

    private final UpdateProductPort updateProductPort;
    private final ProductMapper productMapper;
    private final UpdateProductSizePort updateProductSizePort;
    private final SizePersistenceAdapter sizePersistenceAdapter;
    @Override
    @Transactional
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
                new ProductVo.ProductAggregateIdentifier(null),
                brand, category,productComponentEntityVos
        );

        ProductEntity updateProductEntity = updateProductPort.updateProduct(updateProductVO);
        return productMapper.mapToDomainEntity(updateProductEntity);
    }

    @Override
    @Transactional
    public boolean updateProductQuantity(UpdateProductQuantityCommand command) {

         sizePersistenceAdapter.updateQuantity(
                new SizeVo.SizeId(command.getSizeId()),
                command.getAmount()
         );

        return true;
    }

    @Override
    public void updateProductQuantity(OrderToUpdateProductCommand command) {

        SizeVo sizeVo = SizeVo.createGenerateSizeVo(
                new SizeVo.SizeId(command.getSizeId()),
                new SizeVo.Size(0),
                new SizeVo.Quantity(command.getAmount())
        );

        updateProductSizePort.updateProductSize(sizeVo);

//        DeliveryInfoRequest deliveryInfoRequest = DeliveryInfoRequest.builder()
//                .address(command.getAddress())
//                .orderId(command.getOrderId())
//                .sizeId(command.getSizeId())
//                .build();

//        requestDeliveryPort.sendCreateOrderEvent(deliveryInfoRequest);
    }
}
