package org.example.coupon.adapter.out.persistence.repository;

import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity,Long> {
}
