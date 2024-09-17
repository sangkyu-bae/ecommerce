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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDateTime;
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

    @GetMapping("/coupon/testtest")
    public ResponseEntity<Object> test() throws IOException {
        URL url = new URL("https://oauth2.cert.toss.im/token");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");

        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpConn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
        writer.write("grant_type=client_credentials&" +
                "client_id=test_a8e23336d673ca70922b485fe806eb2d&" +
                "client_secret=test_418087247d66da09fda1964dc4734e453c7cf66a7a9e3&" +
                "scope=ca");
        writer.flush();
        writer.close();

        httpConn.getOutputStream().close();
        InputStream responseStream = httpConn.getResponseCode() == 200
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println(response);

        return ResponseEntity.ok().body(response);
    }
}
