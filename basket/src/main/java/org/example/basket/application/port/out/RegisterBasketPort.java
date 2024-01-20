package org.example.basket.application.port.out;

import org.example.basket.adapter.out.persistence.entity.BasketEntity;
import org.example.basket.domain.Basket;

public interface RegisterBasketPort {

    BasketEntity registerBasket(Basket basket);
}
