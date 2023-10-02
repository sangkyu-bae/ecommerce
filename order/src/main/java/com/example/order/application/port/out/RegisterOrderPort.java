package com.example.order.application.port.out;

import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.module.domain.order.orderentity.OrderVo;

public interface RegisterOrderPort {

    OrderEntity createOrder(OrderVo orderVo);
}
