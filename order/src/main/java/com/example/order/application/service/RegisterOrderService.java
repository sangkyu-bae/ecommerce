package com.example.order.application.service;

import com.example.order.adapter.out.persistence.OrderMapper;
import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.application.port.in.command.RegisterOrderCommand;
import com.example.order.application.port.in.usecase.RegisterOrderUseCase;
import com.example.order.application.port.out.RegisterOrderPort;
import com.example.order.module.domain.order.orderentity.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
@Slf4j
public class RegisterOrderService implements RegisterOrderUseCase {

    private final RegisterOrderPort registerOrderPort;
    private final OrderMapper orderMapper;
    @Override
    public OrderVo registerOrder(RegisterOrderCommand command) {

        int status = OrderVo.StatusCode.ORDER.getStatus();

        OrderVo createOrder = OrderVo.createGenerateOrderVo(
                new OrderVo.OrderId(0),
                new OrderVo.OrderProductId(command.getProductId()),
                new OrderVo.OrderColorId(command.getColorId()),
                new OrderVo.OrderSizeId(command.getSizeId()),
                new OrderVo.OrderAmount(command.getAmount()),
                new OrderVo.OrderPayment(command.getPayment()),
                new OrderVo.OrderAddress(command.getAddress()),
                new OrderVo.OrderCreateAt(LocalDate.now()),
                new OrderVo.OrderUpdateAt(LocalDate.now()),
                new OrderVo.OrderStatus(status)
        );

        OrderEntity createOrderEntity = registerOrderPort.createOrder(createOrder);

        return orderMapper.mapToDomainEntity(createOrderEntity);
    }
}
