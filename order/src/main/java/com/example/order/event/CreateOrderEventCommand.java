package com.example.order.event;

import com.example.order.application.port.in.command.RegisterOrderCommand;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class CreateOrderEventCommand {

    private final EventCommand eventCommand;

    private final RegisterOrderCommand registerOrderCommand;

    public CreateOrderEventCommand(EventCommand eventCommand, RegisterOrderCommand registerOrderCommand){
        this.eventCommand = eventCommand;
        this.registerOrderCommand = registerOrderCommand;
    }
}
