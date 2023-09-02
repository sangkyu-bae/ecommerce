package com.example.order.module.domain.order.service;

import com.example.order.module.common.method.CRUDReadService;
import com.example.order.module.domain.order.enitity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderReadService implements CRUDReadService<Order> {
    @Override
    public Order read(Long id) {
        return null;
    }

    @Override
    public List<Order> readAll() {
        return null;
    }
}
