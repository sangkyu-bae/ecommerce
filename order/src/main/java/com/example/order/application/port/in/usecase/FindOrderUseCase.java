package com.example.order.application.port.in.usecase;

import com.example.order.application.port.in.command.FindMemberOrderListByMemberIdsCommand;
import com.example.order.application.port.in.command.FindOrderCommand;
import com.example.order.domain.OrderVo;

import java.util.List;

public interface FindOrderUseCase {

    OrderVo findOrder(FindOrderCommand command);

    List<OrderVo> findMemberOrderListByMemberIds(FindMemberOrderListByMemberIdsCommand command);
}
