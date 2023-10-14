package com.example.order.application.service;

import com.example.order.adapter.out.persistence.OrderMapper;
import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.adapter.out.persistence.repository.OrderEntityRepository;
import com.example.order.application.port.in.command.FindOrderCommand;
import com.example.order.application.port.in.usecase.FindOrderUseCase;
import com.example.order.application.port.out.FindOrderPort;
import com.example.order.common.UseCase;
import com.example.order.module.domain.order.orderentity.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class FindOrderService implements FindOrderUseCase {

    private final FindOrderPort findOrderPort;
    private final OrderMapper orderMapper;


    @Override
    public OrderVo findOrder(FindOrderCommand command) {
        OrderVo.OrderId orderId = new OrderVo.OrderId(command.getOrderId());
        OrderEntity orderEntity = findOrderPort.findOrder(orderId);

        return orderMapper.mapToDomainEntity(orderEntity);
    }
}
