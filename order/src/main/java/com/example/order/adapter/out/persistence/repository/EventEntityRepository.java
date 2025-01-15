package com.example.order.adapter.out.persistence.repository;

import com.example.order.adapter.out.persistence.entity.EventEntity;
import com.example.order.adapter.out.persistence.entity.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventEntityRepository extends JpaRepository<EventEntity,String> {

    EventEntity findByIdAndEventStatus(String id, EventStatus eventStatus);
}
