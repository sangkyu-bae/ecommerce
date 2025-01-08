package com.example.adminservice.adapter.out.axon;

import com.example.adminservice.adapter.axon.command.ProductCreateCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AxonProductProducer {

    private final CommandGateway commandGateway;

    public void sendProductToAxon(ProductCreateCommand axonCommand){
        commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.error("throwable = " + throwable);
                throw new RuntimeException(throwable);
            } else{
                System.out.println("result = " + result);
            }
        });
    }
}
