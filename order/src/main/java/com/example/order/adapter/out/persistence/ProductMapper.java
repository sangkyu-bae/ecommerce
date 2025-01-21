package com.example.order.adapter.out.persistence;

import com.example.order.adapter.out.persistence.entity.ProductEntity;
import com.example.order.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public Product mapToDomain(ProductEntity productEntity){
        return Product.createGenerateProduct(
                productEntity.getId(),
                productEntity.getProductId(),
                productEntity.getProductName(),
                productEntity.getSizeId(),
                productEntity.getAmount(),
                productEntity.getOrderAmount(),
                productEntity.getCouponId(),
                productEntity.getSalePrice(),
                productEntity.getCreateAt(),
                productEntity.getUpdateAt()
        );
    }
}
