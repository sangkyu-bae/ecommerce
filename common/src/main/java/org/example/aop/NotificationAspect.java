package org.example.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.lang.EvaluationContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.aop.kafka.NotificationProducer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import org.springframework.expression.spel.support.StandardEvaluationContext;
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationAspect {

    private final ExpressionParser parser = new SpelExpressionParser();

    private final NotificationProducer notificationProducer;

    @AfterReturning("@annotation(org.example.aop.Notification)")
    public void sendNotification(final ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Notification notification = method.getAnnotation(Notification.class);

        StandardEvaluationContext context = CustomSpringELParser.setContext(signature.getParameterNames(),joinPoint.getArgs());

        long memberId = parser.parseExpression(notification.memberId()).getValue(context, Long.class);
        String eventName = parser.parseExpression(notification.eventName()).getValue(context, String.class);
        String noti = parser.parseExpression(notification.notification()).getValue(context, String.class);
        int type = parser.parseExpression(notification.type()).getValue(context, Integer.class);

        NotificationClient notificationClient = NotificationClient.createGenerateNotificationClient(
                new NotificationClient.NotificationClientFromMember(memberId),
                new NotificationClient.NotificationClientEventName(eventName),
                new NotificationClient.NotificationNotification(noti),
                NotificationClient.NotificationType.findNotificationType(type)
        );

        notificationProducer.sendCreateNotification(notificationClient);
    }
}