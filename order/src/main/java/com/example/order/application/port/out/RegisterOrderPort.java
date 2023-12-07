package com.example.order.application.port.out;

import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.domain.OrderVo;

public interface RegisterOrderPort {

    OrderEntity createOrder(OrderVo orderVo);
}
