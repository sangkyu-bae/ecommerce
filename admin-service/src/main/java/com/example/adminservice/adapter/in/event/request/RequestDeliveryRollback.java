package com.example.adminservice.adapter.in.event.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class RequestDeliveryRollback {

    private long orderId;

    private int quantity;

    private long sizeId;
}
