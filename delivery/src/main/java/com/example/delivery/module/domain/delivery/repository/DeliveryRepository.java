package com.example.delivery.module.domain.delivery.repository;

import com.example.delivery.module.domain.delivery.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
}
