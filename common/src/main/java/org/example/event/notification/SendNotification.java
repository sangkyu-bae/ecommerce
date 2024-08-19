package org.example.event.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.EnumMapperValue;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendNotification {

    private long fromMember;

    private String eventName;

    private String notification;

    private EnumMapperValue eventType;

    private EnumMapperValue statusType;
}
