package com.example.adminservice.module.domain.brand.repository;

import com.example.adminservice.module.domain.brand.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BrandRepository extends JpaRepository<Brand,Long> {
    boolean existsByName(String name);
}
