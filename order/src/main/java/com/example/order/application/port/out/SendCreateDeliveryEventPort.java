package com.example.order.application.port.out;

import com.example.order.adapter.out.external.delivery.DeliveryEvent;
import com.example.order.adapter.out.external.delivery.OrderInfoRequest;

public interface SendCreateDeliveryEventPort {

    void createDeliveryEvent(DeliveryEvent event);
}
