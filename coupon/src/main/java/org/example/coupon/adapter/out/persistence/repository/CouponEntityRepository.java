package org.example.coupon.adapter.out.persistence.repository;

import org.example.coupon.adapter.out.persistence.entity.CouponEntity;
import org.example.coupon.domain.CouponVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponEntityRepository extends JpaRepository<CouponEntity,Long> {
}
