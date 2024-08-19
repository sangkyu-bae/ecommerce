package org.example.notification.adapter.out.persistence;

import lombok.*;
import org.example.EnumMapperValue;

@Data
@NoArgsConstructor@AllArgsConstructor
@Builder @ToString
public class NotificationResponse {

    private EnumMapperValue eventType;

    private EnumMapperValue statusType;

    private String sendMessage;


}
