package com.example.order.application.port.in.command;

import lombok.*;
import org.example.SelfValidating;

@Data
@NoArgsConstructor @Builder @EqualsAndHashCode(callSuper = true)
public class RemoveOrderCommand extends SelfValidating<RemoveOrderCommand> {

    private long orderId;

    private long userId;

    public RemoveOrderCommand(long orderId,long userId){
        this.userId = userId;
        this.orderId = orderId;
        this.validateSelf();
    }
}
