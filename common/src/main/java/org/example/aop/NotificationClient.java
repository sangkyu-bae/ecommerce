package org.example.aop;

import lombok.*;

import java.util.Arrays;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationClient {

    private long formMember;

    private String eventName;

    private String notification;

    private NotificationType type;

    public static NotificationClient createGenerateNotificationClient(NotificationClientFromMember member,
                              NotificationClientEventName name ,
                              NotificationNotification noti,
                              NotificationType notificationType){
        return new NotificationClient(
                member.getId(),
                name.eventName,
                noti.getNotification(),
                notificationType
        );
    }


    @Value
    public static class NotificationClientFromMember{

        long id;

        public NotificationClientFromMember(long value){
            this.id = value;
        }
    }


    @Value
    public static class NotificationClientEventName{

        String eventName;

        public NotificationClientEventName(String value){
            this.eventName = value;
        }
    }

    @Value
    public static class NotificationNotification{
        String notification;

        public NotificationNotification(String value){
            this.notification = value;
        }
    }

    public static enum NotificationType{

        QUEUE_EVENT(0,"대기열 이벤트");

        private final int type;


        private final String name;

        NotificationType(int type, String name){
            this.type = type;
            this.name = name;
        }

        public int getType(){
            return this.type;
        }

        public String getName(){
            return this.name;
        }

        public static NotificationType findNotificationType(int type){
            return Arrays.stream(NotificationType.values())
                    .filter(couponStatus -> couponStatus.getType()== type)
                    .findFirst()
                    .orElseThrow();
        }

    }
}
