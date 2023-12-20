package org.example.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data @NoArgsConstructor @AllArgsConstructor
public class CheckRegisteredMemberCommand {

    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    private String createOrderId;

    private long memberId;

}
