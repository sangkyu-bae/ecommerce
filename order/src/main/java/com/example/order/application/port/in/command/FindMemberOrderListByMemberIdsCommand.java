package com.example.order.application.port.in.command;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FindMemberOrderListByMemberIdsCommand extends SelfValidating<FindMemberOrderListByMemberIdsCommand> {

    @NotNull
    private final List<Long> memberIds;

    public FindMemberOrderListByMemberIdsCommand(List<Long> memberIds){
        this.memberIds = memberIds;
    }
}
