package org.example.basket.application.port.in.command;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegisterBasketCommand extends SelfValidating<RegisterBasketCommand> {

    @NotNull
    private long memberId;

    @NotNull
    private long productSizeId;

    @NotNull
    private int quantity;

    public RegisterBasketCommand(long memberId,long productSizeId, int quantity){
        this.memberId = memberId;
        this.productSizeId = productSizeId;
        this.quantity = quantity;
        this.validateSelf();
    }
}
