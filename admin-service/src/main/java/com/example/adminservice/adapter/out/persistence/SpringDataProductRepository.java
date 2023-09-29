package com.example.adminservice.adapter.out.persistence;

import com.example.adminservice.adapter.out.persistence.product.entity.ProductEntity;
import com.example.adminservice.domain.product.repository.querydsl.ProductRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SpringDataProductRepository extends JpaRepository<ProductEntity,Long>, ProductEntityRepositoryExtension {

}
