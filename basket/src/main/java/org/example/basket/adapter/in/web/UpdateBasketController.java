package org.example.basket.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.example.basket.application.port.in.command.UpdateBasketQuantityCommand;
import org.example.basket.application.port.in.usecase.UpdateBasketUseCase;
import org.example.basket.domain.Basket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RequiredArgsConstructor
@RestController
@Slf4j
public class UpdateBasketController {

    private final UpdateBasketUseCase updateBasketUseCase;

    @Operation(summary = "update basket", description = "장바구니 수량 수정 하기")
    @PatchMapping("/basket/{basketId}/{quantity}")
    public ResponseEntity<Basket> updateBasket(@PathVariable("basketId") long basketId,
                                               @PathVariable("quantity") int quantity,
                                               @RequestHeader("X-User-Id") Long userId){

        UpdateBasketQuantityCommand command  = UpdateBasketQuantityCommand.builder()
                .memberId(userId)
                .basketId(basketId)
                .quantity(quantity)
                .build();

        Basket updateBasket = updateBasketUseCase.updateBasket(command);

        return ResponseEntity.ok().body(updateBasket);
    }
}
