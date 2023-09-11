package com.example.adminservice.application.port.out;

import com.example.adminservice.domain.product.dto.ProductVo;

public interface RegisterProductPort {

    void createProduct(
            ProductVo.ProductName productName,
            ProductVo.ProductPrice productPrice,
            ProductVo.ProductDescription productDescription,
            ProductVo.ProductImage productImage
    );
}
