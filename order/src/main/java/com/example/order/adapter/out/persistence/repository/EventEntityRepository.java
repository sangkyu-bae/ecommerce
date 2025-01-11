package com.example.order.adapter.out.persistence.repository;

import com.example.order.adapter.out.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventEntityRepository extends JpaRepository<EventEntity,Long> {
}
