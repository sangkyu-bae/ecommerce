package com.example.adminservice.adapter.in.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class DeliveryInfoRequest {

    private Long sizeId;

    private Long orderId;

    private String address;

    private String userId;

}
