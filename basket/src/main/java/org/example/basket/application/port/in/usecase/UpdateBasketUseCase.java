package org.example.basket.application.port.in.usecase;

import org.example.basket.application.port.in.command.UpdateBasketQuantityCommand;
import org.example.basket.domain.Basket;

public interface UpdateBasketUseCase {

    Basket updateBasket(UpdateBasketQuantityCommand command);
}
