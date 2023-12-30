package org.example.event.rollback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RollbackProductFinishedEvent {

    private String rollbackProductId;

    private String rollBackProductAggregateIdentifier;

    private Long sizeId;

    private int amount;


}
