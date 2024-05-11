package com.example.order.application.port.in.command;

import com.example.order.adapter.in.request.RegisterOrderRq;
import lombok.*;
import org.checkerframework.checker.signature.qual.BinaryNameOrPrimitiveType;
import org.example.SelfValidating;

import javax.validation.Validation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "sizeId")
public class RegisterOrderCd extends SelfValidating<RegisterOrderRq> {

    @NotNull
    private long sizeId;

    @NotNull
    private int amount;

    @NotNull
    private int payment;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    private long userId;

    private Long couponId;

    private String aggregateIdentifier;

    public RegisterOrderCd(long sizeId,
                           int amount,
                           int payment,
                           String address,
                           long userId,
                           Long couponId,
                           String aggregateIdentifier){
        this.sizeId = sizeId;
        this.amount = amount;
        this.payment =payment;
        this.address = address;
        this.userId = userId;
        this.couponId = couponId;
        this.aggregateIdentifier = aggregateIdentifier;
        this.validateSelf();
    }
}
