package org.example.ranking.application.port.in.command;

import lombok.*;
import org.example.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegisterRankingCommand extends SelfValidating<RegisterRankingCommand> {

    @NotNull
    private long productId;


    public RegisterRankingCommand(long productId){
        this.productId = productId;
        this.validateSelf();
    }
}
