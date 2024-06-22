package com.example.order.application.port.in.command;

import lombok.*;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor @EqualsAndHashCode(callSuper = true)
public class FindOrderByMemberIdCommand extends SelfValidating<FindOrderByMemberIdCommand> {

    @NotNull
    private long userId;

    public FindOrderByMemberIdCommand(long userId){
        this.userId = userId;
    }
}
