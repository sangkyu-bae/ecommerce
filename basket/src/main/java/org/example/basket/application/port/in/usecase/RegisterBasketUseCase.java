package org.example.basket.application.port.in.usecase;

import org.example.basket.application.port.in.command.RegisterBasketCommand;
import org.example.basket.domain.Basket;

public interface RegisterBasketUseCase {

    Basket RegisterBasket(RegisterBasketCommand command);
}
