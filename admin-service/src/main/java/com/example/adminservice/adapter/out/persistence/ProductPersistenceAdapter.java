package com.example.adminservice.adapter.out.persistence;

import com.example.adminservice.application.port.out.RegisterProductPort;
import com.example.adminservice.common.PersistenceAdapter;
import com.example.adminservice.common.WebAdapter;
import com.example.adminservice.domain.brand.entity.Brand;
import com.example.adminservice.domain.category.entity.Category;
import com.example.adminservice.domain.product.dto.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class ProductPersistenceAdapter implements RegisterProductPort {

    private final SpringDataProductRepository springDataProductRepository;


    @Override
    public ProductEntity createProduct(ProductVo.ProductName productName,
                              ProductVo.ProductPrice productPrice,
                              ProductVo.ProductDescription productDescription,
                              ProductVo.ProductImage productImage, BrandEntity brand, CategoryEntity category, Set<ProductComponent> productComponents) {

        ProductEntity createProductEntity = ProductEntity.builder()
                .name(productName.getProductName())
                .price(productPrice.getPrice())
                .description(productDescription.getDescription())
                .productImage(productImage.getImage())
                .brand(brand)
                .category(category)
                .productComponents(productComponents)
                .build();

        return springDataProductRepository.save(createProductEntity);
    }
}
