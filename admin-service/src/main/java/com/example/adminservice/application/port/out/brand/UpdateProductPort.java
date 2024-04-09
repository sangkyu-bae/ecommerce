package com.example.adminservice.application.port.out.brand;

import com.example.adminservice.adapter.out.persistence.entity.ProductEntity;
import com.example.adminservice.domain.ProductVo;

public interface UpdateProductPort {
    ProductEntity updateProduct(ProductVo productVo);
}
