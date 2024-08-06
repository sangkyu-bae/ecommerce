package org.example.coupon.adapter.out.persistence.repository;

import org.example.coupon.adapter.out.persistence.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponEntityRepository extends JpaRepository<CouponEntity,Long> {
    List<CouponEntity> findByName(String name);
}
