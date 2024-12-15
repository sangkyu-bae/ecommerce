package org.example.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.adapter.out.service.Basket;
import org.example.application.port.in.command.SuggestCommand;
import org.example.application.port.in.usecase.SuggestProductUseCase;
import org.example.application.port.out.GetBasketPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@UseCase
@Service
@RequiredArgsConstructor
@Slf4j
public class FindSuggestService implements SuggestProductUseCase {

    private final GetBasketPort getBasketPort;
    @Override
    public Map<String, Object> findSuggestProduct(SuggestCommand command) {

        List <Basket> basketList = getBasketPort.getBasket();



        return null;
    }
}
