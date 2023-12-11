package com.example.order.adapter.in.web;


import com.example.order.adapter.in.request.RegisterOrderRequest;
import com.example.order.application.port.in.command.RegisterOrderCommand;
import com.example.order.application.port.in.usecase.RegisterOrderUseCase;
import com.example.order.domain.OrderVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@WebAdapter
public class RegisterOrderController {

    private final RegisterOrderUseCase registerOrderUseCase;
    @Operation(summary = "register order", description = "주문 등록")
    @PostMapping("/order/register")
    public ResponseEntity<OrderVo> registerOrder(@RequestBody RegisterOrderRequest request ,
                                                 @RequestHeader("X-User-Id") Long userId) throws JsonProcessingException {

        RegisterOrderCommand command = RegisterOrderCommand.builder()
                .productId(request.getProductId())
                .colorId(request.getColorId())
                .sizeId(request.getSizeId())
                .amount(request.getAmount())
                .payment(request.getPayment())
                .status(RegisterOrderCommand.StatusCode.ORDER.getStatus())
                .userId(userId)
                .build();

        OrderVo orderVo = registerOrderUseCase.registerOrder(command);

        return ResponseEntity.ok().body(orderVo);
    }

}
