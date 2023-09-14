package com.example.adminservice.application.port.out;

import com.example.adminservice.adapter.out.persistence.*;
import com.example.adminservice.domain.brand.entity.Brand;
import com.example.adminservice.domain.category.entity.Category;
import com.example.adminservice.domain.product.dto.ProductVo;

import java.util.Set;

public interface RegisterProductPort {

    ProductEntity createProduct(
            ProductVo.ProductName productName,
            ProductVo.ProductPrice productPrice,
            ProductVo.ProductDescription productDescription,
            ProductVo.ProductImage productImage,
            BrandEntity brand,
            CategoryEntity category,
            Set<ProductComponent> productComponents
    );
}
