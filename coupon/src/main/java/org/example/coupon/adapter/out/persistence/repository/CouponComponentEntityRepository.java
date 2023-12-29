package org.example.coupon.adapter.out.persistence.repository;

import org.example.coupon.adapter.out.persistence.entity.CouponComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponComponentEntityRepository extends JpaRepository<CouponComponentEntity,Long> {
}
