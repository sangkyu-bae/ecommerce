package com.example.order.application.port.out;

import com.example.order.adapter.out.external.product.ProductInfoRequest;
import com.example.order.module.domain.order.orderentity.OrderVo;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RequestProductInfoPort {
    void createOrderEvent(ProductInfoRequest productInfoRequest) throws JsonProcessingException;
}
