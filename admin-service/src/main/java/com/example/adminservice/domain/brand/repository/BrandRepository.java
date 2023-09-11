package com.example.adminservice.domain.brand.repository;

import com.example.adminservice.domain.brand.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface BrandRepository extends JpaRepository<Brand,Long> {
    boolean existsByName(String name);
    Optional<Brand> findByName(String brandName);
}
