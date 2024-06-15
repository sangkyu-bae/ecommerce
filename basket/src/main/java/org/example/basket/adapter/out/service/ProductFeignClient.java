package org.example.basket.adapter.out.service;

import org.example.basket.infra.config.FeignClientInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "admin-service",configuration = FeignClientInterceptorConfig.class)
public interface ProductFeignClient {

    @GetMapping("/admin/find/product-size/{sizeId}")
    boolean existProductBySizeId(@PathVariable("sizeId") long sizeId);

    @GetMapping("/admin/product-list")
    List<Product> findByProductIds(@RequestBody List<Long> productIds);
}
