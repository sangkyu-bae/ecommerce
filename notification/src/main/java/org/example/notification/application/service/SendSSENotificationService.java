package org.example.notification.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.aop.NotificationClient;
import org.example.notification.application.port.in.usecase.SendSSENotificationUseCase;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class SendSSENotificationService implements SendSSENotificationUseCase {
    @Override
    public void sendMessage(NotificationClient client) {

    }
}
