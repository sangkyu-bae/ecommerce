package com.example.adminservice.application.port.out;

import com.example.adminservice.adapter.out.persistence.ProductComponent;
import com.example.adminservice.adapter.out.persistence.ProductEntity;
import com.example.adminservice.adapter.out.persistence.SizeEntity;
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
            Brand brand,
            Category category,
            Set<ProductComponent> productComponents
    );
}
