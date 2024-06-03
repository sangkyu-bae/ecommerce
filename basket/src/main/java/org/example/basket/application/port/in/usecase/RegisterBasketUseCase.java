package org.example.basket.application.port.in.usecase;

import org.example.basket.application.port.in.command.RegisterBasketCommand;
import org.example.basket.domain.Basket;

import java.util.List;

public interface RegisterBasketUseCase {

    Basket RegisterBasket(RegisterBasketCommand command);

    List<Basket> RegisterBaskets(List<RegisterBasketCommand> commands);
}
