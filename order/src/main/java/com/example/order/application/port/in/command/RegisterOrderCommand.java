package com.example.order.application.port.in.command;

import lombok.*;
import org.example.SelfValidating;

import java.util.List;


@Builder @Data
@AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(callSuper = true)
public class RegisterOrderCommand extends SelfValidating<RegisterOrderCommand> {


    private int payment;

    private String address;

    private long userId;

    private String aggregateIdentifier;


    private String phone;

    List<ProductCommand> productCommands;

    @Value
    @Builder @AllArgsConstructor
    public static class ProductCommand {

        private long productId;

        private long colorId;

        private long sizeId;

        private int amount;

        private Long couponId;

        private String productName;

        private int orderAmount;
    }
}
