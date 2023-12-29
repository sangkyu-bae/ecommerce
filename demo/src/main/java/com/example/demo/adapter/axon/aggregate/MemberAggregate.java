package com.example.demo.adapter.axon.aggregate;

//import com.example.demo.adapter.axon.command.CheckRegisteredMemberCommand;
//import com.example.demo.adapter.axon.event.CreateRegisteredMemberEvent;
import com.example.demo.adapter.axon.command.CreateRegisterMemberCommand;
import com.example.demo.adapter.axon.event.CreateRegisteredMemberEvent;
import com.example.demo.application.port.out.FindMemberPort;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.example.event.CheckRegisteredMemberCommand;
import org.example.event.CheckRegisteredMemberEvent;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
@Aggregate()
@Slf4j
public class MemberAggregate {

    @AggregateIdentifier
    private String id;

    private String email;

    private String name;

    private String address;

    private boolean emailVerified;

    private String emailCheckToken;

    private String phone;

    private boolean notificationByEmail;

    private LocalDateTime joinAt;

    private LocalDateTime updateAt;

    @CommandHandler
    public MemberAggregate(CreateRegisterMemberCommand command){
        log.info("CreateRegisteredMemberCommand Sourcing Handler");
        apply(new CreateRegisteredMemberEvent(
                command.getEmail(),
                command.getName(),
                command.getPassword()
        ));
    }
    @EventSourcingHandler
    public void on (CreateRegisteredMemberEvent event){
        this.id = UUID.randomUUID().toString();
        this.email = event.getEmail();
        this.name = event.getName();
        this.address = null;
        this.emailVerified = false;
        this.emailCheckToken = null;
        this.phone = null;
        this.notificationByEmail = false;
        this.joinAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();

    }
    @CommandHandler
    public void handler(CheckRegisteredMemberCommand command, FindMemberPort findMemberPort){
        log.info("CreateRegisteredMemberCommand Sourcing Handler");
        boolean existMember = findMemberPort.existMember(command.getUserId());
        id = command.getAggregateIdentifier();

        apply(new CheckRegisteredMemberEvent(
                command.getUserId(),
                command.getCreateOrderId(),
                command.getCheckRegisteredMemberId(),
                command.getProductId(),
                command.getSizeId(),
                command.getAmount(),
                command.getPayment(),
                command.getAddress(),
                existMember,
                command.getCouponId()
        ));
    }

    public MemberAggregate(){

    }

}
