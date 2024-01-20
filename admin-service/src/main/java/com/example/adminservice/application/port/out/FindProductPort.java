package com.example.adminservice.application.port.out;

import com.example.adminservice.adapter.out.persistence.product.entity.ProductEntity;
import com.example.adminservice.domain.ProductVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FindProductPort {

    ProductEntity findProduct(ProductVo.ProductId productId);

    Page<ProductEntity> findPagingProduct(Pageable pageable);

    boolean existProductBySize(long sizeId );
}
