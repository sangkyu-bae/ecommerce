package com.example.adminservice.adapter.in.web.request.productRequest;

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

}
