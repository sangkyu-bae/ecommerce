package org.example.basket.application.port.in.usecase;

import org.example.basket.application.port.in.command.RemoveBasketCommand;

public interface RemoveBasketUseCase {
    boolean removeBasket(RemoveBasketCommand command);
}
