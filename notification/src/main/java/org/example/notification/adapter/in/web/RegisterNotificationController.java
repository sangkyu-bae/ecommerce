package org.example.notification.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.example.notification.application.port.in.command.RegisterSSENotificationCommand;
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
    @Operation(summary = "register notification ", description = "알림 구독 하기")
    @GetMapping(value = "/notification/{eventName}", produces = "text/event-stream")
//    public ResponseEntity<String> subscribe(@RequestHeader("X-User-Id") long userId ,
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SseEmitter> subscribe(
            @PathVariable("eventName") String eventName) {

        RegisterSSENotificationCommand command = RegisterSSENotificationCommand.builder()
                .userId(1)
                .eventName(eventName)
                .build();
        SseEmitter emitter = registerSSENotificationUseCase.subscribe(command);
        return ResponseEntity.ok().body(emitter);
    }


    @GetMapping("/notification/sendAll")
    public void sendAll(){

        registerNotificationPort.sendAllMessage();
    }
}
