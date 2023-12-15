package com.example.order.application.port.in.command;

import lombok.*;
import org.example.SelfValidating;

@Builder @Data
@NoArgsConstructor @EqualsAndHashCode(callSuper = true)
public class FailRemoveOrderCommand extends SelfValidating<FailRemoveOrderCommand> {

    private long orderId;

    public FailRemoveOrderCommand(long orderId){
        this.orderId = orderId;
        this.validateSelf();
    }
}
