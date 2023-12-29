package com.example.order.adapter.out.service;

import com.example.order.application.port.out.GetCouponPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponServiceAdapter implements GetCouponPort {

    private final CouponFeignClient couponFeignClient;

    @Override
    public Coupon getCoupon(long couponId) {
        Coupon coupon = couponFeignClient.getCoupon(couponId);
        return coupon;
    }
}
