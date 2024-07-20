package com.example.adminservice.application.port.out.brand;

import com.example.adminservice.adapter.out.persistence.entity.ProductEntity;
import com.example.adminservice.domain.ProductVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface FindProductPort {

    ProductEntity findProduct(ProductVo.ProductId productId);

    Page<ProductEntity> findPagingProduct(Pageable pageable);

    Page<ProductEntity> findPagingProductByCategory(Pageable pageable, long categoryId);

    boolean existProductBySize(long sizeId );

    List<ProductEntity> findProductAll();

    List<ProductEntity> findProductByProductIds(List<Long> productIds);
}
