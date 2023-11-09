package com.example.order.adapter.in.web;

import com.example.order.application.port.in.command.FindOrderCommand;
import com.example.order.application.port.in.usecase.FindOrderUseCase;
import com.example.order.module.domain.order.orderentity.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@WebAdapter
@Slf4j
public class FindOrderController {

    private final FindOrderUseCase findOrderUseCase;

    @GetMapping("/order/find/{orderId}")
    public ResponseEntity<OrderVo> findOrder(@PathVariable("orderId") long orderId){
        FindOrderCommand command = FindOrderCommand.builder()
                .orderId(orderId)
                .build();

        OrderVo orderVo = findOrderUseCase.findOrder(command);

        return ResponseEntity.ok().body(orderVo);
    }
}
