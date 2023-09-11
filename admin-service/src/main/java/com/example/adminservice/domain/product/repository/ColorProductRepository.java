package com.example.adminservice.domain.product.repository;

import com.example.adminservice.domain.product.entity.ColorProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ColorProductRepository extends JpaRepository<ColorProduct,Long> {
}
