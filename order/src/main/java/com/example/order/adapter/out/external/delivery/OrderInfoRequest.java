package com.example.order.adapter.out.external.delivery;

import com.example.order.application.port.in.command.RegisterOrderCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class OrderInfoRequest {

    private Long orderId;

    private Long productId;

    private Long colorId;

    private int amount;

    private String address;

    private String userId;

    public static OrderInfoRequest createGenerateOrderRequest(RegisterOrderCommand command){
        return OrderInfoRequest.builder()
                .orderId(command.getColorId())
                .productId(command.getProductId())
                .colorId(command.getColorId())
                .amount(command.getAmount())
                .address(command.getAddress())
                .userId(command.getUseremail())
                .build();
    }
}
