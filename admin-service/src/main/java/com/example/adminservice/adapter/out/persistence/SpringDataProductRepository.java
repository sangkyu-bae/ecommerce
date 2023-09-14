package com.example.adminservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SpringDataProductRepository extends JpaRepository<ProductEntity,Long> {
}