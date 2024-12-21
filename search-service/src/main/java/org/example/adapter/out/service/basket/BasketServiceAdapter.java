package org.example.adapter.out.service.basket;

import lombok.RequiredArgsConstructor;
import org.example.application.port.out.GetBasketPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasketServiceAdapter implements GetBasketPort {
    private final BasketFeignClient basketFeignClient;

    @Override
    public List<Basket> getBasket() {
        return basketFeignClient.getProductBasket();
    }
}
