package org.example.basket.application.port.in.command;

import lombok.*;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateBasketQuantityCommand extends SelfValidating<UpdateBasketQuantityCommand> {

    @NotNull
    private long memberId;

    @NotNull
    private long basketId;

    @NotNull
    private int quantity;

    public UpdateBasketQuantityCommand(long memberId, long basketId, int quantity){
        this.memberId = memberId;
        this.basketId = basketId;
        this.quantity = quantity;
        this.validateSelf();
    }
}
