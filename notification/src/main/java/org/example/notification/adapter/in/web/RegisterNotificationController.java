package org.example.notification.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.example.notification.application.factory.ConcertNotificationFactory;
import org.example.notification.application.factory.NotificationFactory;
import org.example.notification.application.port.in.command.NotificationType;
import org.example.notification.application.port.in.command.RegisterSSENotificationCommand;
import org.example.notification.application.port.in.usecase.Notification;
import org.example.notification.application.port.in.usecase.RegisterSSENotificationUseCase;
import org.example.notification.application.port.out.RegisterNotificationPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterNotificationController {

    private final RegisterSSENotificationUseCase registerSSENotificationUseCase;
    private final RegisterNotificationPort registerNotificationPort;
    private final NotificationFactory notificationFactory;
    //    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "register notification ", description = "알림 구독 하기")
    @GetMapping(value = "/notification/{eventName}", produces = "text/event-stream")
    public ResponseEntity<SseEmitter> subscribe(@RequestHeader("X-User-Id") long userId ,
                                            @PathVariable("eventName") String eventName){
        RegisterSSENotificationCommand command = RegisterSSENotificationCommand.builder()
                .userId(userId)
                .eventName(eventName)
                .build();
        SseEmitter emitter = registerSSENotificationUseCase.subscribe(command);
        return ResponseEntity.ok().body(emitter);
    }


    @Operation(summary = "register notification ", description = "이벤트 쿠폰 알림 구독 하기")
    @GetMapping(value = "/notification/coupon/{eventName}", produces = "text/event-stream")
    public ResponseEntity<SseEmitter> subscribeByEventCoupon(@RequestHeader("X-User-Id") long userId ,
                                                @PathVariable("eventName") String eventName){
        RegisterSSENotificationCommand command = RegisterSSENotificationCommand.builder()
                .userId(userId)
                .eventName(eventName)
                .build();

        Notification notification = notificationFactory.createNotification(NotificationType.EVENT_COUPON);

        SseEmitter emitter = notification.subscribe(command);
        return ResponseEntity.ok().body(emitter);
    }



    @GetMapping("/notification/sendAll")
    public void sendAll(){

        registerNotificationPort.sendAllMessage();
    }
}