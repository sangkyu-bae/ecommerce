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
@EqualsAndHashCode(callSuper = true)
public class FindBasketCommand extends SelfValidating<FindBasketCommand> {

    @NotNull
    private long memberId;


    public FindBasketCommand(long memberId){
        this.memberId = memberId;
        this.validateSelf();
    }
}
