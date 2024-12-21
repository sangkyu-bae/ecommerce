package org.example.application.port.in.command;

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
public class SuggestCommand extends SelfValidating<SuggestCommand> {

    @NotNull
    private long userId;

    public SuggestCommand(long userId){
        this.userId = userId;
        this.validateSelf();
    }
}
