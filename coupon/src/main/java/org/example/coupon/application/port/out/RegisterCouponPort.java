package org.example.coupon.application.port.out;

import org.example.coupon.adapter.out.persistence.entity.CouponComponentEntity;
import org.example.coupon.adapter.out.persistence.entity.CouponEntity;
import org.example.coupon.domain.Coupon;
import org.example.coupon.domain.CouponComponent;

public interface RegisterCouponPort {
    CouponEntity registerCouponByAllUser(Coupon couponVo);

    CouponComponentEntity issuanceCoupon(CouponComponent couponComponent, Coupon.CouponId couponId);
}
