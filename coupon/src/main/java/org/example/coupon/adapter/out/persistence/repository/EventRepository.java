package org.example.coupon.adapter.out.persistence.repository;

import org.example.coupon.adapter.out.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity,Long> {

    List<EventEntity> findByStartAtBefore(LocalDateTime startAt);

    List<EventEntity> findByStartAtBeforeAndEndAtGreaterThanEqual(LocalDateTime startAt, LocalDateTime endAt);
}
