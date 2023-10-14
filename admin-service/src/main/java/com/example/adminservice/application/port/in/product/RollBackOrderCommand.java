package com.example.adminservice.application.port.in.product;

import com.example.adminservice.common.SelfValidating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class RollBackOrderCommand extends SelfValidating<RollBackOrderCommand> {

    private long orderId;
    private int quantity;
    private long sizeId;
}
