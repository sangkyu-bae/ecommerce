package com.example.delivery.application.port.in.command;

import com.example.delivery.application.service.RollbackDeliveryService;
import lombok.*;
import org.example.SelfValidating;

@Data
@AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(callSuper = true)
@Builder
public class RequestRollbackDeliveryCommand extends SelfValidating<RollbackDeliveryService> {
    private long orderId;
    private int quantity;
    private long sizeId;
}
