package org.example.coupon.adapter.out.service;

import org.example.coupon.infra.config.FeignClientInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "auth-service",configuration = FeignClientInterceptorConfig.class)
public interface MemberFeignClient {

    @GetMapping("/user/all")
    List<Member> getMemberList();
}
