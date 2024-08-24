package org.example.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.kafka.NotificationProducer;
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

    @Around("@annotation(org.example.aop.Notification)")
    public Object sendNotification(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.error(">>>>>>>>>>>>>>>>> sendNoti");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Notification notification = method.getAnnotation(Notification.class);

        log.error(">>>>>>>>>>>>>>>>> {}",notification.toString());
        StandardEvaluationContext context = CustomSpringELParser.setContext(signature.getParameterNames(), joinPoint.getArgs());

        long memberId = parser.parseExpression(notification.memberId()).getValue(context, Long.class);
        String eventName = "";
        String noti = notification.notification();
        int type = 0;

        log.info("member Id : {}",memberId);
        log.info("eventName : {}",eventName);
        log.info("noti : {}",noti);
        log.info("type : {}",memberId);
//        String eventName = parser.parseExpression(notification.eventName()).getValue(context, String.class);
//        String noti = parser.parseExpression(notification.notification()).getValue(context, String.class);
//        int type = parser.parseExpression(notification.type()).getValue(context, Integer.class);

        NotificationClient notificationClient = NotificationClient.createGenerateNotificationClient(
                new NotificationClient.NotificationClientFromMember(memberId),
                new NotificationClient.NotificationClientEventName(eventName),
                new NotificationClient.NotificationNotification(noti),
                NotificationClient.NotificationType.findNotificationType(type)
        );

        // 실제 메소드 실행
        Object result = joinPoint.proceed();

        // 메소드 실행 후에 알림 전송
        notificationProducer.sendCreateNotification(notificationClient);

        // 메소드의 결과 반환
        return result;
    }

//    private final ExpressionParser parser = new SpelExpressionParser();
//
//    private final NotificationProducer notificationProducer;
//
//    @AfterReturning("@annotation(org.example.aop.Notification)")
//    public void sendNotification(final ProceedingJoinPoint joinPoint) {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        Notification notification = method.getAnnotation(Notification.class);
//
//        StandardEvaluationContext context = CustomSpringELParser.setContext(signature.getParameterNames(),joinPoint.getArgs());
//
//        long memberId = parser.parseExpression(notification.memberId()).getValue(context, Long.class);
//        String eventName = parser.parseExpression(notification.eventName()).getValue(context, String.class);
//        String noti = parser.parseExpression(notification.notification()).getValue(context, String.class);
//        int type = parser.parseExpression(notification.type()).getValue(context, Integer.class);
//
//        NotificationClient notificationClient = NotificationClient.createGenerateNotificationClient(
//                new NotificationClient.NotificationClientFromMember(memberId),
//                new NotificationClient.NotificationClientEventName(eventName),
//                new NotificationClient.NotificationNotification(noti),
//                NotificationClient.NotificationType.findNotificationType(type)
//        );
//
//        notificationProducer.sendCreateNotification(notificationClient);
//    }
}