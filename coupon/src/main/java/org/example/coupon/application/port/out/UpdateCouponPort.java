package org.example.coupon.application.port.out;

import org.example.coupon.adapter.out.persistence.entity.CouponEntity;
import org.example.coupon.domain.CouponComponent;

import java.util.List;

public interface UpdateCouponPort {
    CouponEntity updateCoupon(long couponId, long userId);

    CouponEntity updateCouponComponent(List<CouponComponent> couponComponentList, CouponEntity fetchCoupon);

    void bulkInsertCouponComponent(List<CouponComponent> couponComponentList, CouponEntity fetchCoupon);
}
