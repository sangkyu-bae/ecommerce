package org.example.adapter.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.example.application.CouponProxyService;
import org.example.application.RegisterCouponCommand;
import org.example.dto.RegisterEventCoupon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@Slf4j
public class CouponProxyController {

    private final CouponProxyService couponProxyService;

    @PostMapping("/coupon-proxy/v2/{eventId}")
    public ResponseEntity<String> proxyCoupon(@PathVariable("eventId") long eventId,
                                              @RequestHeader("X-User-Id") long userId){
        RegisterCouponCommand command = RegisterCouponCommand.builder()
                .eventId(eventId)
                .userId(userId)
                .build();


        String res = couponProxyService.execute(command);

        if(res.equals("fail")){
            return ResponseEntity.status(405).body(res);
        }

        return  ResponseEntity.ok().body(res);
    }

    @PostMapping("/coupon-proxy/test/{eventId}")
    public ResponseEntity<String> test(@PathVariable("eventId") long eventId){

        for(int i = 1; i <= 500; i++){
            RegisterCouponCommand command = RegisterCouponCommand.builder()
                    .eventId(eventId)
                    .userId(i)
                    .build();

            couponProxyService.execute(command);
        }

        return ResponseEntity.ok().body("ok");
    }
}
