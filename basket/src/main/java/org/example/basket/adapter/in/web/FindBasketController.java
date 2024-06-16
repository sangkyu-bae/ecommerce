package org.example.basket.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.example.basket.application.port.in.command.FindBasketCommand;
import org.example.basket.application.port.in.usecase.FindBasketUseCase;
import org.example.basket.domain.Basket;
import org.example.basket.domain.BasketAggregationVo;
import org.example.basket.infra.error.BasketErrorCode;
import org.example.basket.infra.error.ErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@Slf4j
public class FindBasketController {

    private final FindBasketUseCase findBasketUseCase;

    @Operation(summary = "find basket", description = "유저 장바구니 조회하기")
    @GetMapping("/basket")
    public ResponseEntity<List<Basket>> findBasket(@RequestHeader("X-User-Id") Long userId){

        List<Basket> findBasketList = null;

        try{
            FindBasketCommand command = FindBasketCommand.builder()
                    .memberId(userId)
                    .build();
            findBasketList = findBasketUseCase.findBasketList(command);
        }catch (ConstraintViolationException e){
            throw new ErrorException(BasketErrorCode.BASKET_NOT_FOUND,"findBasket()");
        }

        return ResponseEntity.ok().body(findBasketList);
    }

    @Operation(summary = "find basket Api Aggregation", description = "유저 장바구니 조회하기 상품 이름 포함 한 api 조합")
    @GetMapping("/basket/aggregation")
    public ResponseEntity<List<BasketAggregationVo>> findBasketAggregation(@RequestHeader("X-User-Id") Long userId){

        FindBasketCommand command = FindBasketCommand.builder()
                .memberId(userId)
                .build();

        List<BasketAggregationVo> basketAggregationVos = findBasketUseCase.findBasketAggregationList(command);

        return ResponseEntity.ok().body(basketAggregationVos);
    }
}
