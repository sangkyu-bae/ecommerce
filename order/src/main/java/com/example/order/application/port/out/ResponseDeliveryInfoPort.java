package com.example.order.application.port.out;

import com.example.order.adapter.out.external.delivery.OrderInfoRequest;

public interface ResponseDeliveryInfoPort {
    void orderInformationToDelivery(OrderInfoRequest orderInfoRequest);
}
