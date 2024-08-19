package org.example.notification.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.example.notification.application.port.in.command.RegisterSSENotificationCommand;
import org.example.notification.application.port.in.usecase.RegisterSSENotificationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterNotificationController {

    private final RegisterSSENotificationUseCase registerSSENotificationUseCase;
    @Operation(summary = "register notification ", description = "알림 구독 하기")
    @GetMapping(value = "/notification/{eventName}", produces = "text/event-stream")
    public ResponseEntity<String> subscribe(@RequestHeader("X-User-Id") long userId ,
                                            @PathVariable("eventName") String eventName) {

        RegisterSSENotificationCommand command = RegisterSSENotificationCommand.builder()
                .userId(userId)
                .eventName(eventName)
                .build();
        registerSSENotificationUseCase.subscribe(command);
        return ResponseEntity.ok().body("connect");
    }
}
