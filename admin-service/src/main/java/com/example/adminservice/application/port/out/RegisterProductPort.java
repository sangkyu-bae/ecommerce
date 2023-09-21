package com.example.adminservice.application.port.out;

import com.example.adminservice.adapter.out.persistence.product.ProductEntity;
import com.example.adminservice.domain.product.dto.ProductVo;

public interface RegisterProductPort {

    ProductEntity createProduct(ProductVo productVo);
}
