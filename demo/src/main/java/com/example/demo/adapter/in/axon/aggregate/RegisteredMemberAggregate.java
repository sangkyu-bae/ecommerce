package com.example.demo.adapter.in.axon.aggregate;

import com.example.demo.adapter.in.axon.command.CheckRegisteredMemberCommand;
import com.example.demo.adapter.in.axon.event.CreateRegisteredMemberEvent;
import com.example.demo.application.port.out.FindMemberPort;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
@Aggregate()
@Slf4j
public class RegisteredMemberAggregate {

    @AggregateIdentifier
    private String id;

    private long memberId;

    @CommandHandler
    public RegisteredMemberAggregate(CheckRegisteredMemberCommand command, FindMemberPort findMemberPort){
        log.info("CreateRegisteredMemberCommand Sourcing Handler");
        boolean existMember = findMemberPort.existMember(command.getMemberId());

        apply(new CreateRegisteredMemberEvent(
                command.getMemberId(),
                command.getCreateOrderId(),
                command.getProductId(),
                command.getColorId(),
                command.getSizeId(),
                command.getAmount(),
                command.getPayment(),
                command.getAddress(),
                existMember
        ));
    }

    public RegisteredMemberAggregate(){

    }

}
