package com.example.order.adapter.in.web;


import com.example.order.adapter.in.web.request.RegisterOrderRequest;
import com.example.order.application.port.in.command.RegisterOrderCommand;
import com.example.order.application.port.in.usecase.RegisterOrderUseCase;
import com.example.order.module.domain.order.orderentity.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegisterOrderController {

    private final RegisterOrderUseCase registerOrderUseCase;

    @PostMapping("/order/register")
    public ResponseEntity<OrderVo> registerOrder(@RequestBody RegisterOrderRequest request){

        RegisterOrderCommand command = RegisterOrderCommand.builder()
                .productId(request.getProductId())
                .colorId(request.getColorId())
                .sizeId(request.getSizeId())
                .amount(request.getAmount())
                .payment(request.getPayment())
                .status(RegisterOrderCommand.StatusCode.ORDER.getStatus())
                .build();

        OrderVo orderVo = registerOrderUseCase.registerOrder(command);

        return ResponseEntity.ok().body(orderVo);
    }

}
