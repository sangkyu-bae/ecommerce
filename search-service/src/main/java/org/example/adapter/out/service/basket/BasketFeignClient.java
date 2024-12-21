package org.example.adapter.out.service.basket;

import org.example.infra.config.FeignClientInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "basket-service",configuration = FeignClientInterceptorConfig.class)
public interface BasketFeignClient {

    @GetMapping("/basket")
    List<Basket> getProductBasket();
}
