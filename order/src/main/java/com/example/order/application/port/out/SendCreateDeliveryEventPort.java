package com.example.order.application.port.out;

import com.example.order.adapter.out.external.delivery.DeliverySendCommand;

public interface SendCreateDeliveryEventPort {

    void createDeliveryEvent(DeliverySendCommand event);
}
