package com.example.order.application.port.out;

import com.example.order.adapter.out.persistence.entity.OrderEntity;

import java.util.List;

public interface GetMemberOrderPort {

    List<OrderEntity> getMemberOrderPort(List<Long> memberIds);
}
