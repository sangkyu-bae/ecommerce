package com.example.adminservice.adapter.in.web.request.productRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderToUpdateProductRequest {

    private int amount;

    private Long sizeId;

    private Long orderId;

    private String address;
}
