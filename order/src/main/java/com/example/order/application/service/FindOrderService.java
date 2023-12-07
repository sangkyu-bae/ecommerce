package com.example.order.application.service;

import com.example.order.adapter.out.persistence.OrderMapper;
import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.application.port.in.command.FindMemberOrderListByMemberIdsCommand;
import com.example.order.application.port.in.command.FindOrderCommand;
import com.example.order.application.port.in.usecase.FindOrderUseCase;
import com.example.order.application.port.out.FindOrderPort;

import com.example.order.application.port.out.GetMemberOrderPort;
import com.example.order.domain.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class FindOrderService implements FindOrderUseCase {

    private final FindOrderPort findOrderPort;
    private final OrderMapper orderMapper;

    private final GetMemberOrderPort getMemberOrderPort;


    @Override
    public OrderVo findOrder(FindOrderCommand command) {
        OrderVo.OrderId orderId = new OrderVo.OrderId(command.getOrderId());
        OrderEntity orderEntity = findOrderPort.findOrder(orderId);

        return orderMapper.mapToDomainEntity(orderEntity);
    }

    @Override
    public List<OrderVo> findMemberOrderListByMemberIds(FindMemberOrderListByMemberIdsCommand command) {
        List<OrderEntity> orders = getMemberOrderPort.getMemberOrderPort(command.getMemberIds());
        List<OrderVo> orderVoList = orders.stream()
                .map(order -> orderMapper.mapToDomainEntity(order))
                .collect(Collectors.toList());


        return orderVoList;
    }
}
