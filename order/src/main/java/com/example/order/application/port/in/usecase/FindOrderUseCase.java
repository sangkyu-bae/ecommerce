package com.example.order.application.port.in.usecase;

import com.example.order.application.port.in.command.FindMemberOrderListByMemberIdsCommand;
import com.example.order.application.port.in.command.FindOrderByMemberIdCommand;
import com.example.order.application.port.in.command.FindOrderByMemberPagingCommand;
import com.example.order.application.port.in.command.FindOrderCommand;
import com.example.order.domain.OrderAggregationVo;
import com.example.order.domain.OrderVo;
import com.example.order.domain.SearchOrder;

import java.util.List;

public interface FindOrderUseCase {

    OrderVo findOrder(FindOrderCommand command);

    List<OrderVo> findMemberOrderListByMemberIds(FindMemberOrderListByMemberIdsCommand command);

    List<OrderAggregationVo> findOrderListByMemberId(FindOrderByMemberIdCommand command);

    SearchOrder findOrderByMemberIdPaging(FindOrderByMemberPagingCommand command);
}
