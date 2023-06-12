package com.example.adminservice.module.domain.quantity.repository;

import com.example.adminservice.module.domain.quantity.entity.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface QuantityRepository extends JpaRepository<Quantity,Long> {
}
