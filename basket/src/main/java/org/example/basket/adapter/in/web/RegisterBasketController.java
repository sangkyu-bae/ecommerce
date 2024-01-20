package org.example.basket.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.example.basket.adapter.in.request.RegisterBasketRequest;
import org.example.basket.application.port.in.command.RegisterBasketCommand;
import org.example.basket.application.port.in.usecase.RegisterBasketUseCase;
import org.example.basket.domain.Basket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBasketController {

    private final RegisterBasketUseCase registerBasketUseCase;

    @Operation(summary = "register basket", description = "장바구니 등록")
    @PostMapping("/basket")
    public ResponseEntity<Basket> registerBasket(
            @RequestBody RegisterBasketRequest request,
            @RequestHeader("X-User-Id") Long userId
    ){
        RegisterBasketCommand command = RegisterBasketCommand.builder()
                .memberId(userId)
                .productSizeId(request.getProductSizeId())
                .quantity(request.getQuantity())
                .build();

        Basket registerBasket = registerBasketUseCase.RegisterBasket(command);

        return ResponseEntity.ok().body(registerBasket);
    }
}
