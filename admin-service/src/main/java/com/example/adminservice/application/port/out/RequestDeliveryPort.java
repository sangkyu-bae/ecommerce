package com.example.adminservice.application.port.out;

import com.example.adminservice.adapter.in.web.request.productRequest.DeliveryInfoRequest;

public interface RequestDeliveryPort {
    void sendCreateOrderEvent(DeliveryInfoRequest deliveryInfoRequest);
}
