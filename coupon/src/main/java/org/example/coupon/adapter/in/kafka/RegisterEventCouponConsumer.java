package org.example.coupon.adapter.in.kafka;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.coupon.application.port.in.command.UpdateEventCouponCommand;
import org.example.coupon.application.port.in.usecase.UpdateEventCouponUseCase;
import org.example.dto.RegisterEventCoupon;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegisterEventCouponConsumer {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final UpdateEventCouponUseCase updateEventCouponUseCase;


    @KafkaListener(topics ="${kafka.coupon.proxy.create.topic}",groupId = "${kafka.coupon.proxy.create.group}")
    public void listener(String registerEventCouponMsg){
        RegisterEventCoupon eventCoupon = null;

        try{
            eventCoupon = objectMapper.readValue(registerEventCouponMsg, RegisterEventCoupon.class);

            UpdateEventCouponCommand command = UpdateEventCouponCommand.builder()
                    .eventId(eventCoupon.getEventId())
                    .userId(eventCoupon.getUserId())
                    .build();

            updateEventCouponUseCase.addEventQueue(command);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
