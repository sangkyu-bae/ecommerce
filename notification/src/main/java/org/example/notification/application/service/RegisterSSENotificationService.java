package org.example.notification.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.event.notification.RegisterSSECommand;
import org.example.notification.application.port.in.command.RegisterSSENotificationCommand;
import org.example.notification.application.port.in.usecase.RegisterSSENotificationUseCase;
import org.example.notification.application.port.out.RegisterNotificationPort;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class RegisterSSENotificationService implements RegisterSSENotificationUseCase {

    private final RegisterNotificationPort registerNotificationPort;

    @Override
    public SseEmitter subscribe(RegisterSSENotificationCommand command) {
        return registerNotificationPort.subscribe(command.getUserId(),command.getEventName(),"연결중입니다.");
    }

    @Override
    public void subscribe(RegisterSSECommand command) {
        registerNotificationPort.subscribe(command);
    }
}
