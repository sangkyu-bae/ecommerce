package com.example.adminservice.module.domain.product.repository;

import com.example.adminservice.module.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ProductRepository extends JpaRepository<Product,Long> {
}
