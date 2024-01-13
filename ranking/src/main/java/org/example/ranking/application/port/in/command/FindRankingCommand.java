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
public class FindRankingCommand extends SelfValidating<FindRankingCommand> {

    @NotNull
    private int size;

    public FindRankingCommand(int size){
        this.size = size;
        this.validateSelf();
    }
}
