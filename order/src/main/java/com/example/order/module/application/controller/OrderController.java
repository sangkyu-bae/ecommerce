package com.example.order.module.application.controller;

import com.example.order.module.application.usecase.OrderUseCase;
import com.example.order.module.domain.order.dto.OrderDto;
import com.example.order.module.domain.order.enitity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderUseCase orderUseCase;

    @PostMapping("/order/{productId}")
    public Order createOrder(@PathVariable("productId") Long productId,
                             @RequestBody OrderDto createOrderDto,
                             @RequestHeader("X-User-Id") String userId){
        Order order = null;

        try{
            createOrderDto.setUserId(userId);
            createOrderDto.setProductId(productId);
            order = orderUseCase.createOrder(createOrderDto);
        }catch (Exception e){
            log.error("create Error", e);
        }

        return order;
    }

}
