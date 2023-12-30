package org.example.event.rollback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RollbackRequestProductCommand {

    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    private Long sizeId;

    private int amount;

    private String rollbackProductId;

}
