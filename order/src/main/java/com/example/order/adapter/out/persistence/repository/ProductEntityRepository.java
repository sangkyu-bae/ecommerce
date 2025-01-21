package com.example.order.adapter.out.persistence.repository;

import com.example.order.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity,Long> {



}
