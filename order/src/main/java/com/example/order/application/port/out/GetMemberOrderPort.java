package com.example.order.application.port.out;

import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.module.domain.order.enitity.Order;

import java.util.List;

public interface GetMemberOrderPort {

    List<OrderEntity> getMemberOrderPort(List<Long> memberIds);
}
