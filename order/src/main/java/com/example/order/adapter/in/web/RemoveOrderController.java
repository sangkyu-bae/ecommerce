package com.example.order.adapter.in.web;

import com.example.order.application.port.in.command.RemoveOrderCommand;
import com.example.order.application.port.in.usecase.RemoveOrderUseCase;
import com.example.order.infra.error.ErrorException;
import com.example.order.infra.error.OrderErrorCode;
import com.example.order.vaildator.RemoveOrderCommandValidator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RemoveOrderController {

    private final RemoveOrderUseCase removeOrderUseCase;

    private final RemoveOrderCommandValidator validator;

    @DeleteMapping("/order/{orderId}")
    @Operation(summary = "remove order By Id", description = "주문 취소 하기")
    public ResponseEntity<String> removeOrder(@PathVariable("orderId") long orderId,
                                              @RequestHeader("X-User-Id") long userId){

        RemoveOrderCommand command = RemoveOrderCommand.builder()
                .orderId(orderId)
                .userId(userId)
                .build();

        Errors errors = new BeanPropertyBindingResult(command,"command");
        validator.validate(command,errors);
        if(errors.hasErrors()){
            throw new ErrorException(OrderErrorCode.ORDER_NO_VALIDATE,"removeOrder");
        }

        removeOrderUseCase.removeOrder(command);

        return ResponseEntity.ok().body("success remove order :"+ orderId);
    }
}
