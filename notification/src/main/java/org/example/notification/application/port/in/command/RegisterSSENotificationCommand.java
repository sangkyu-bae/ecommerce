package org.example.notification.application.port.in.command;

import lombok.*;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterSSENotificationCommand extends SelfValidating<RegisterSSENotificationCommand> {

    @NotNull
    private long userId;

    @NotNull
    private String eventName;

    public RegisterSSENotificationCommand(long userId,String eventName){
        this.userId = userId;
        this.eventName = eventName;
        this.validateSelf();
    }
}
