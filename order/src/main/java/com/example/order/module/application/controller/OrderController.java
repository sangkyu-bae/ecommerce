package com.example.order.module.application.controller;

import com.example.order.module.application.usecase.OrderUseCase;
import com.example.order.module.domain.order.dto.OrderDto;
import com.example.order.module.domain.order.enitity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderUseCase orderUseCase;

    @PostMapping("/order/{productId}/{userId}")
    public Order createOrder(@PathVariable("productId") Long productId,
                             @PathVariable("userId") Long userId,
                             @RequestBody OrderDto createOrderDto){
        Order order = null;
        try{
            createOrderDto.setProductId(userId);
            order = orderUseCase.createOrder(createOrderDto);
        }catch (Exception e){
            log.error("create Error", e);
        }
        return order;
    }

}
