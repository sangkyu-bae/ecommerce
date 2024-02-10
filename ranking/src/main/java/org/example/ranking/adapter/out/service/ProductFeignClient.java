package org.example.ranking.adapter.out.service;

import org.example.ranking.infra.config.FeignClientInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "admin-service",configuration = FeignClientInterceptorConfig.class)
public interface ProductFeignClient {

    @GetMapping("/admin")
    List<Product> getProductAll();
}
