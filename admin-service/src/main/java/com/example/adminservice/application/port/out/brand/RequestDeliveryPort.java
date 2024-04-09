package com.example.adminservice.application.port.out.brand;

import com.example.adminservice.adapter.in.request.DeliveryInfoRequest;

public interface RequestDeliveryPort {
    void sendCreateOrderEvent(DeliveryInfoRequest deliveryInfoRequest);
}
