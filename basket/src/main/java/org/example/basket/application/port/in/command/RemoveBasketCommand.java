package org.example.basket.application.port.in.command;

import lombok.*;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class RemoveBasketCommand extends SelfValidating<RemoveBasketCommand> {

    @NotNull
    private long basketId;

    @NotNull
    private long memberId;

    public RemoveBasketCommand(long basketId, long memberId){
        this.basketId = basketId;
        this.memberId = memberId;
        this.validateSelf();
    }


}
