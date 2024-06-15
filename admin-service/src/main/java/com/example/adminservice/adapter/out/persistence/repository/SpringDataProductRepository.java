package com.example.adminservice.adapter.out.persistence.repository;

import com.example.adminservice.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SpringDataProductRepository extends JpaRepository<ProductEntity,Long>, ProductEntityRepositoryExtension {
    List<ProductEntity> findByIdIn(List<Long> productIds);
}
