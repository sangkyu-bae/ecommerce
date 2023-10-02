package com.example.order.adapter.out.persistence.repository;

import com.example.order.adapter.out.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderEntityRepository extends JpaRepository<OrderEntity,Long> {
}
