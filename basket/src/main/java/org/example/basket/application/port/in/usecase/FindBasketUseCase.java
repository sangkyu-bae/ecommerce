package org.example.basket.application.port.in.usecase;

import org.example.basket.application.port.in.command.FindBasketCommand;
import org.example.basket.domain.Basket;
import org.example.basket.domain.BasketAggregationVo;

import java.util.List;

public interface FindBasketUseCase {
    List<Basket> findBasketList(FindBasketCommand command);

    List<BasketAggregationVo> findBasketAggregationList(FindBasketCommand command);
}
