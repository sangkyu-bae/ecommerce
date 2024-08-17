package org.example.event.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.aop.NotificationClient;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestNotification {

    private long fromMember;

    private String eventName;

    private String notification;

    private SSENotificationType type;

    private SSEStatusType statusType;
}
