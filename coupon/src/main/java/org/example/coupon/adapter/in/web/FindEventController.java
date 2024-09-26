package org.example.coupon.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.example.coupon.application.port.in.command.FindAuthEventCouponCommand;
import org.example.coupon.application.port.in.command.FindEventCouponCommand;
import org.example.coupon.application.port.in.usecase.FindEventUseCase;
import org.example.coupon.domain.Event;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

@RestController
@RequiredArgsConstructor
@WebAdapter
public class FindEventController {

    private final FindEventUseCase findEventUseCase;

    @Operation(summary = "find event coupon", description = "이벤트 쿠폰 가져오기 비로그인 조회")
    @GetMapping("/coupon/basic/event")
    public ResponseEntity<List<Event>> findEventCoupon(){

        FindEventCouponCommand command = FindEventCouponCommand.builder()
                .startAt(LocalDateTime.now())
                .endAt(LocalDateTime.now())
                .build();

        List<Event> eventList = findEventUseCase.findEventCoupon(command);

        return ResponseEntity.ok().body(eventList);
    }

    @Operation(summary = "find event coupon", description = "이벤트 쿠폰 가져오기")
    @GetMapping("/coupon/auth/event")
    public ResponseEntity<List<Event>> findWithAuthByEventCoupon(@RequestHeader("X-User-Id") Long userId){

        FindAuthEventCouponCommand command = FindAuthEventCouponCommand.builder()
                .startAt(LocalDateTime.now())
                .endAt(LocalDateTime.now())
                .userId(userId)
                .build();

        List<Event> eventList = findEventUseCase.findWithAuthByEventCoupon(command);

        return ResponseEntity.ok().body(eventList);
    }
}
