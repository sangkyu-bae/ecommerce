package com.example.order.application.port.in.command;


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
public class FindOrderByMemberPagingCommand extends SelfValidating<FindOrderByMemberPagingCommand> {

    @NotNull
    private long userId;

    @NotNull
    private int pageNum;

    public FindOrderByMemberPagingCommand(long userId,int pageNum){
        this.userId = userId;
        this.pageNum = pageNum;
    }
}
