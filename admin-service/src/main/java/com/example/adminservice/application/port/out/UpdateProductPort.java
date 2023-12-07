package com.example.adminservice.application.port.out;

import com.example.adminservice.adapter.out.persistence.product.entity.ProductEntity;
import com.example.adminservice.domain.ProductVo;

public interface UpdateProductPort {
    ProductEntity updateProduct(ProductVo productVo);
}
