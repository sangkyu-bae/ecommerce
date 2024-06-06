package org.example.basket.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.basket.adapter.out.persistence.BasketMapper;
import org.example.basket.adapter.out.persistence.entity.BasketEntity;
import org.example.basket.application.port.in.command.FindBasketCommand;
import org.example.basket.application.port.in.usecase.FindBasketUseCase;
import org.example.basket.application.port.out.FindBasketPort;
import org.example.basket.domain.Basket;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class FindBasketService implements FindBasketUseCase {

    private final FindBasketPort findBasketPort;

    private final BasketMapper basketMapper;
    @Override
    public List<Basket> findBasketList(FindBasketCommand command) {
        Basket.BasketMemberId basketMemberId = new Basket.BasketMemberId(command.getMemberId());
        List<BasketEntity> basketEntityList = findBasketPort.findBasketList(basketMemberId);

        return basketEntityList.stream()
                .map(basketEntity -> basketMapper.mapToDomainEntity(basketEntity))
                .collect(Collectors.toList());
    }
}
