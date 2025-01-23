package com.example.order.adapter.axon.command;

import com.example.order.application.port.in.command.RegisterOrderCommand;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestCreateCommand {

    private String createOrderId;

    private int payment;

    private String address;

    private int status;

    private long userId;

    private List<ProductRequestCommand> productRequestCommands;

}
