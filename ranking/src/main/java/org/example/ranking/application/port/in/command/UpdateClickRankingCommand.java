package org.example.ranking.application.port.in.command;

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
public class UpdateClickRankingCommand extends SelfValidating<UpdateClickRankingCommand> {

    @NotNull
    private String productName;

    @NotNull
    private long productId;

    public UpdateClickRankingCommand(String productName, long productId){
        this.productName = productName;
        this.productId = productId;
        this.validateSelf();
    }
}
