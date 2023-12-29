package com.example.order.application.port.in.command;

import lombok.*;
import org.example.SelfValidating;


@Builder @Data
@AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(callSuper = true)
public class RegisterOrderCommand extends SelfValidating<RegisterOrderCommand> {

    private long productId;

    private long colorId;

    private long sizeId;

    private int amount;

    private int payment;

    private String address;

    private long userId;

    private Long couponId;

}
