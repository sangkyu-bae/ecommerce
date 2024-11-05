package org.example.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.adapter.kafka.RegisterCouponProducer;
import org.example.dto.RegisterEventCoupon;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponProxyService {

    private final RegisterCouponProducer registerCouponProducer;
    public String execute(RegisterCouponCommand command){

        try{
            RegisterEventCoupon coupon = RegisterEventCoupon.createGenerate(command.getUserId(),command.getEventId());
            registerCouponProducer.send(coupon);
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }

        return "success";
    }
}
