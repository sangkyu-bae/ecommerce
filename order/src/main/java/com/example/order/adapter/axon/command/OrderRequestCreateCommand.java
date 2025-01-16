package com.example.order.adapter.axon.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestCreateCommand {

    private String createOrderId;

    private int payment;

    private String address;

    private int status;

    private long userId;

    private List<ProductRequestCommand> productRequestCommands;

    @Value
    public class ProductRequestCommand{
        private long productId;

        private long colorId;

        private long sizeId;

        private int amount;

        private Long couponId;
    }
}
