package org.example.basket.application.port.out;

import org.example.basket.domain.Basket;

public interface RemoveBasketPort {
    void removeBasket(Basket.BasketId basketId);
}
