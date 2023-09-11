package com.example.adminservice.domain.quantity.repository;

import com.example.adminservice.domain.quantity.entity.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.Optional;

@Transactional(readOnly = true)
public interface QuantityRepository extends JpaRepository<Quantity,Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select q from Quantity q where q.id = :quantityId")
    Optional<Quantity> findWithIdForUpdate(@Param("quantityId") Long quantityId);
}
