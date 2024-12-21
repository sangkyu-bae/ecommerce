package org.example.adapter.out.service.product;

import org.example.infra.config.FeignClientInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "admin-service",configuration = FeignClientInterceptorConfig.class)

public interface ProductFeignClient {
    @PostMapping("/admin/product-list")
    List<Product> findByProductIds(@RequestBody List<Long> productIds);
}
