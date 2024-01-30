package org.example.coupon.application.port.out;

import org.example.coupon.adapter.out.persistence.entity.CouponEntity;
import org.example.coupon.domain.Coupon;

public interface FindCouponPort {
    CouponEntity findCouponByCouponId(Coupon.CouponId couponId);
}
