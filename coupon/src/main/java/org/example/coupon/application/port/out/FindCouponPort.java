package org.example.coupon.application.port.out;

import org.example.coupon.adapter.out.persistence.entity.CouponEntity;
import org.example.coupon.domain.CouponVo;

public interface FindCouponPort {
    CouponEntity findCouponByCouponId(CouponVo.CouponId couponId);
}
