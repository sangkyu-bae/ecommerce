package com.example.adminservice.adapter.out.persistence;

import com.example.adminservice.application.port.out.RegisterProductPort;
import com.example.adminservice.common.PersistenceAdapter;
import com.example.adminservice.domain.product.dto.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class ProductPersistenceAdapter implements RegisterProductPort {

    private final SpringDataProductRepository springDataProductRepository;
    @Override
    public void createProduct(ProductVo.ProductName productName, ProductVo.ProductPrice productPrice, ProductVo.ProductDescription productDescription, ProductVo.ProductImage productImage) {

    }
}
