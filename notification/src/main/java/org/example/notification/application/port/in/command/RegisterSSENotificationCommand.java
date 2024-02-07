package org.example.notification.application.port.in.command;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterSSENotificationCommand extends SelfValidating<RegisterSSENotificationCommand> {

    @NotNull
    private long userId;

    public RegisterSSENotificationCommand(long userId){
        this.userId = userId;
        this.validateSelf();
    }
}
