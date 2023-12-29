package com.example.order.adapter.out.service;

import com.example.order.infra.config.FeignClientInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "coupon-service",configuration = FeignClientInterceptorConfig.class)
public interface CouponFeignClient {

    @GetMapping("/coupon/{couponId}")
    Coupon getCoupon(@PathVariable("couponId") long couponId);
}
