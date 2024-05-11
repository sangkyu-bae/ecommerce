package com.example.order.adapter.axon.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.SelfValidating;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestCreateSagaCommand extends SelfValidating<OrderRequestCreateCommand> {

    private String createOrderId;
}
