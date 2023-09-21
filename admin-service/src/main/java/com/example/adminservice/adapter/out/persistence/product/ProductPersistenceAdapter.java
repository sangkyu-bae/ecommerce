package com.example.adminservice.adapter.out.persistence.product;

import com.example.adminservice.adapter.out.persistence.SpringDataProductRepository;
import com.example.adminservice.application.port.out.RegisterProductPort;
import com.example.adminservice.common.WebAdapter;
import com.example.adminservice.domain.product.dto.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

@WebAdapter
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductPersistenceAdapter implements RegisterProductPort {

    private final SpringDataProductRepository springDataProductRepository;

    private final ModelMapper modelMapper;


    @Override
    public ProductEntity createProduct(ProductVo productVo) {

        ProductEntity createProductEntity = ProductEntity.builder()
                .name(productVo.getName())
                .price(productVo.getPrice())
                .description(productVo.getDescription())
                .productImage(productVo.getProductImage())
                .brand(productVo.getBrand())
                .category(productVo.getCategory())
                .productComponents(productVo.getProductComponents())
                .build();

        productVo.getProductComponents()
                .forEach(component -> {
                            component.setProduct(createProductEntity);
                            component.getSizes().forEach(size->size.setProductComponent(component));
                        }
                );

        ProductEntity a = springDataProductRepository.save(createProductEntity);
//        return springDataProductRepository.save(createProductEntity);
        return a;
    }
}
