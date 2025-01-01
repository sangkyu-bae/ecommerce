package com.example.adminservice.adapter.out.persistence.repository;

import com.example.adminservice.adapter.out.persistence.entity.ProductComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductComponentEntityRepository extends JpaRepository<ProductComponentEntity,Long> {
}
