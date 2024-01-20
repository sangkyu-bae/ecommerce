package org.example.basket.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.basket.application.port.in.command.RemoveBasketCommand;
import org.example.basket.application.port.in.usecase.RemoveBasketUseCase;
import org.example.basket.application.port.out.RemoveBasketPort;
import org.example.basket.domain.Basket;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RemoveBasketService implements RemoveBasketUseCase {

    private final RemoveBasketPort removeBasketPort;
    @Override
    public boolean removeBasket(RemoveBasketCommand command) {
        try{
            removeBasketPort.removeBasket(new Basket.BasketId(command.getBasketId()));
        }catch (Exception e){
            log.error("databaseError : {}", e);
            return false;
        }

        return true;
    }
}
