package com.example.demo.adapter.axon.command;


import lombok.*;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CheckRegisteredMemberCommand {

    private long memberId;

    private String createOrderId;

    private long productId;

    private long colorId;

    private long sizeId;

    private int amount;

    private int payment;

    private String address;

    private int status;

    private long userId;
}
