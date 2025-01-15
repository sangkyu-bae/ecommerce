package com.example.order.application.service;

import com.example.order.application.port.in.command.RegisterOrderCommand;
import com.example.order.application.port.in.usecase.RegisterOrderUseCase;
import com.example.order.domain.OrderVo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class RegisterOrderServiceTest {

    @Autowired
    private RegisterOrderUseCase registerOrderUseCase;

    @AfterEach
    void tearDown(){

    }

    @Test
    @DisplayName("상품 주문 하기")
    void registerOrderByEvent() {

        //given

        RegisterOrderCommand command = RegisterOrderCommand.builder()
                .productId(1L)
                .colorId(1L)
                .sizeId(1L)
                .amount(30)
                .payment(5000)
                .address("테스트 주소")
                .userId(1L)
                .couponId(1L)
                .aggregateIdentifier(String.valueOf(UUID.randomUUID()))
                .sequence("1")
                .phone("01026276389")
                .build();

        //when
        OrderVo orderVo;
        try{
            orderVo   = registerOrderUseCase.registerOrderByEvent(command);
        }catch (Exception e){
            e.printStackTrace();
        }
        //then
    }
}