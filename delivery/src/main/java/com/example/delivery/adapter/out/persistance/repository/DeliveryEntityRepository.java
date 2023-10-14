package com.example.delivery.adapter.out.persistance.repository;

import com.example.delivery.adapter.out.persistance.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface DeliveryEntityRepository extends JpaRepository<DeliveryEntity,Long>{
}
