package org.example.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckRegisteredProductCommand {

    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    private String createOrderId;

    private String checkRegisteredProductIdAndAmount;

    private long productId;

    private long sizeId;

    private int amount;
}
