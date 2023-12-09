package com.example.order.adapter.out.service;

import com.example.order.infra.config.FeignClientInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "auth-service",configuration = FeignClientInterceptorConfig.class)
public interface MemberFeignClient {

    @GetMapping("/{memberId}")
    boolean getIsMember(@PathVariable("memberId") long memberId);
}
