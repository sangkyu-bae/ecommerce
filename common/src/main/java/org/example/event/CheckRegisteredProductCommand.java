package org.example.event;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CheckRegisteredProductCommand {

    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    private String createOrderId;

    private String checkRegisteredProductIdAndAmount;

    private Long userId;

    private List<ProductRequestCreateCommand> productRequestCreateEvents;




}
