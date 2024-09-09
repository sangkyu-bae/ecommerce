package org.example.coupon.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.example.coupon.application.port.in.command.FindEventCouponCommand;
import org.example.coupon.application.port.in.usecase.FindEventUseCase;
import org.example.coupon.domain.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@WebAdapter
public class FindEventController {

    private final FindEventUseCase findEventUseCase;

    @Operation(summary = "find event coupon", description = "이벤트 쿠폰 가져오기")
    @GetMapping("/coupon/basic/event")
    public ResponseEntity<List<Event>> findEventCoupon(){

        FindEventCouponCommand command = FindEventCouponCommand.builder()
                .startAt(LocalDateTime.now())
                .endAt(LocalDateTime.now())
                .build();

        List<Event> eventList = findEventUseCase.findEventCoupon(command);

        return ResponseEntity.ok().body(eventList);
    }
}
