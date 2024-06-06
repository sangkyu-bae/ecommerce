package org.example.basket.application.port.out;

import org.example.basket.adapter.out.persistence.entity.BasketEntity;
import org.example.basket.domain.Basket;

import java.util.List;

public interface FindBasketPort {

    List<BasketEntity> findBasketList(Basket.BasketMemberId basketMemberId);
}
