package org.example.event.notification;

import lombok.*;
import org.example.EnumMapperValue;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SendNotification {

    private long fromMember;

    private String eventName;

    private String notification;

    private EnumMapperValue eventType;

    private EnumMapperValue statusType;
}
