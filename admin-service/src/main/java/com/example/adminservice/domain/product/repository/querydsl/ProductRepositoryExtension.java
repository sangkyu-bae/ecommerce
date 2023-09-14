package com.example.adminservice.domain.product.repository.querydsl;

import com.example.adminservice.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ProductRepositoryExtension {
    Page<Product> findWithPageByAll(Pageable pageable);
}
