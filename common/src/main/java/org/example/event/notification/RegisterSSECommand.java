package org.example.event.notification;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterSSECommand extends SelfValidating<RegisterSSECommand> {

    @NotNull
    private long userId;


    @NotNull
    private SSENotificationType notificationType;

    public RegisterSSECommand(long userId, SSENotificationType notificationType){
        this.userId = userId;
        this.notificationType = notificationType;
        this.validateSelf();
    }

}
