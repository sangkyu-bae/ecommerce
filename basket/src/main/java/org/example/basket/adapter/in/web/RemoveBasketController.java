package org.example.basket.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.example.basket.application.port.in.command.RemoveBasketCommand;
import org.example.basket.application.port.in.usecase.RemoveBasketUseCase;
import org.example.basket.application.port.out.RemoveBasketPort;
import org.example.basket.infra.error.BasketErrorCode;
import org.example.basket.infra.error.ErrorException;
import org.example.basket.vaildator.RemoveBasketCommandValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RemoveBasketController {

    private final RemoveBasketCommandValidator validator;

    private final RemoveBasketUseCase removeBasketUseCase;

    @Operation(summary = "remove basket", description = "장바구니 삭제")
    @DeleteMapping("/basket/{basketId}")
    public ResponseEntity<Boolean> removeBasket(
            @PathVariable("basketId") long basketId,
            @RequestHeader("X-User-Id") Long userId){

        RemoveBasketCommand command = RemoveBasketCommand.builder()
                .basketId(basketId)
                .build();

        Errors errors = new BeanPropertyBindingResult(command,"command");
        validator.validate(command,errors);
        if(errors.hasErrors()){
            throw new ErrorException(BasketErrorCode.BASKET_NO_VALIDATE,"removeOrder");
        }

        boolean isRemove = removeBasketUseCase.removeBasket(command);

        return ResponseEntity.ok().body(isRemove);

    }
}
