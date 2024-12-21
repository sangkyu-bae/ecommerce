package org.example.application.port.out;

import org.example.adapter.out.service.basket.Basket;

import java.util.List;

public interface GetBasketPort {
    List<Basket> getBasket();
}
