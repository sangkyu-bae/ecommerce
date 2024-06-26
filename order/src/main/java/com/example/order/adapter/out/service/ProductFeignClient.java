package com.example.order.adapter.out.service;

import com.example.order.infra.config.FeignClientInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "admin-service",configuration = FeignClientInterceptorConfig.class)
public interface ProductFeignClient {

    @GetMapping("/admin/find/{productId}")
    Product getProduct(@PathVariable("productId") long productId);

    @PostMapping("/admin/product-list")
    List<Product> findByProductIds(@RequestBody List<Long> productIds);
}
