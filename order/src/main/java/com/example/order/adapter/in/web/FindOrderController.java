package com.example.order.adapter.in.web;

import com.example.order.adapter.in.request.FindMemberOrderListByMemberIdsRequest;
import com.example.order.application.port.in.command.FindMemberOrderListByMemberIdsCommand;
import com.example.order.application.port.in.command.FindOrderCommand;
import com.example.order.application.port.in.usecase.FindOrderUseCase;
import com.example.order.domain.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/order/member-order")
    void findMemberOrderListByMemberIds(@RequestBody FindMemberOrderListByMemberIdsRequest request){
        FindMemberOrderListByMemberIdsCommand command = FindMemberOrderListByMemberIdsCommand.builder()
                .memberIds(request.getMemberIds())
                .build();

        findOrderUseCase.findMemberOrderListByMemberIds(command);
    }


}
