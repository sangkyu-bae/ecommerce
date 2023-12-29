package com.example.order.application.port.out;

import com.example.order.adapter.out.service.Coupon;

public interface GetCouponPort {
    Coupon getCoupon(long couponId);
}
