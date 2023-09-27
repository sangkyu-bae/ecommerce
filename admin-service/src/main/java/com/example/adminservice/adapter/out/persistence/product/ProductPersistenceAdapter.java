package com.example.adminservice.adapter.out.persistence.product;

import com.example.adminservice.adapter.out.persistence.SpringDataProductRepository;
import com.example.adminservice.adapter.out.persistence.product.entity.ProductEntity;
import com.example.adminservice.application.port.out.FindProductPort;
import com.example.adminservice.application.port.out.RegisterProductPort;
import com.example.adminservice.common.WebAdapter;
import com.example.adminservice.domain.product.dto.ProductVo;
import com.example.adminservice.module.common.error.ErrorException;
import com.example.adminservice.module.common.error.errorImpl.ProductErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@WebAdapter
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductPersistenceAdapter implements RegisterProductPort, FindProductPort {

    private final SpringDataProductRepository springDataProductRepository;

    private final ProductMapper productMapper;


    @Override
    public ProductEntity createProduct(ProductVo productVo) {

        ProductEntity createProductEntity = ProductEntity.builder()
                .name(productVo.getName())
                .price(productVo.getPrice())
                .description(productVo.getDescription())
                .productImage(productVo.getProductImage())
                .brand(productMapper.mapToBrand(productVo.getBrand()))
                .category(productMapper.mapToCategory(productVo.getCategory()))
                .productComponents(productMapper.mapToProductComponents(productVo.getProductComponents()))
                .build();

        createProductEntity.getProductComponents()
                .forEach(component -> {
                            component.setProduct(createProductEntity);
                            component.getSizes().forEach(size->size.setProductComponent(component));
                        }
                );

        return springDataProductRepository.save(createProductEntity);
    }

    @Override
    public ProductEntity findProduct(ProductVo.ProductId productId) {
        return springDataProductRepository.findById(productId.getId())
                .orElseThrow(()-> new ErrorException(ProductErrorCode.PRODUCT_FORM_NO_VALIDATE,"findProduct"));
    }
}
