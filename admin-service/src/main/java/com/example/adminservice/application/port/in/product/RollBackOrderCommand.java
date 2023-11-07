package com.example.adminservice.application.port.in.product;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.SelfValidating;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class RollBackOrderCommand extends SelfValidating<RollBackOrderCommand> {

    private long orderId;
    private int quantity;
    private long sizeId;
}
