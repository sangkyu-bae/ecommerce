package org.example.coupon.application.port.out;

import org.example.coupon.adapter.out.persistence.entity.CouponEntity;

public interface UpdateCouponPort {
    CouponEntity updateCoupon(long couponId, long userId);
}
