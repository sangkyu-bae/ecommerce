package com.example.order.adapter.axon.event;


import com.example.order.adapter.axon.command.OrderRequestCreateCommand;
import com.example.order.adapter.axon.command.ProductRequestCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterOrderCreatedEvent {

    private String createOrderId;

    private int payment;

    private String address;

    private int status;

    private long userId;

    private List<ProductRequestCommand> productRequestCreateEvents;

}
