package org.example.basket.application.port.in.command;

import lombok.*;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false) @ToString
public class RegisterBasketCommand extends SelfValidating<RegisterBasketCommand> {

    @NotNull
    private long memberId;

    @NotNull
    private long productSizeId;

    @NotNull
    private long productId;

    @NotNull
    private int size;

    @NotNull
    private int quantity;

    @NotNull
    private String colorName;

    public RegisterBasketCommand(long memberId,long productSizeId,long productId, int size ,int quantity,String colorName){
        this.memberId = memberId;
        this.productSizeId = productSizeId;
        this.productId = productId;
        this.size = size;
        this.quantity = quantity;
        this.colorName = colorName;
        this.validateSelf();
    }
}
