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

    @NotNull
    @NotBlank
    private String productName;

    public RegisterRankingCommand(long productId, String productName){
        this.productId = productId;
        this.productName = productName;
        this.validateSelf();
    }
}
