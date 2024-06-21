package org.example.basket.application.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.basket.adapter.out.persistence.BasketMapper;
import org.example.basket.adapter.out.persistence.entity.BasketEntity;
import org.example.basket.application.port.in.command.UpdateBasketQuantityCommand;
import org.example.basket.application.port.in.usecase.UpdateBasketUseCase;
import org.example.basket.application.port.out.UpdateBasketPort;
import org.example.basket.domain.Basket;

@UseCase
@Slf4j
@AllArgsConstructor
public class UpdateBasketService implements UpdateBasketUseCase {

    private final UpdateBasketPort updateBasketPort;

    private final BasketMapper basketMapper;

    @Override
    public Basket updateBasket(UpdateBasketQuantityCommand command) {
        BasketEntity updateBasket = updateBasketPort.updateBasketByQuantity(
                new Basket.BasketId(command.getBasketId()),
                new Basket.BasketProductQuantity(command.getQuantity()),
                new Basket.BasketMemberId(command.getMemberId())
        );

        return basketMapper.mapToDomainEntity(updateBasket);
    }
}
