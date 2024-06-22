package com.example.order.application.port.out;

import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.domain.OrderVo;

import java.util.List;

public interface FindOrderPort {
    OrderEntity findOrder(OrderVo.OrderId orderId);

    List<OrderEntity> findOrderByMemberId(OrderVo.OrderProductUserId userId);
}
