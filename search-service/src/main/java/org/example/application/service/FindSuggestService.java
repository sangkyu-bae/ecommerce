package org.example.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.adapter.out.service.basket.Basket;
import org.example.application.port.in.command.SuggestCommand;
import org.example.application.port.in.usecase.SuggestProductUseCase;
import org.example.application.port.out.FindSuggestProductPort;
import org.example.application.port.out.GetBasketPort;
import org.example.application.port.out.GetProductPort;
import org.example.domain.TopProduct;
import org.example.infra.error.ErrorException;
import org.example.infra.error.SearchError;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@UseCase
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FindSuggestService implements SuggestProductUseCase {

    private final GetBasketPort getBasketPort;
    private final GetProductPort getProductPort;

    private final FindSuggestProductPort findSuggestProductPort;
    @Override
    public List<TopProduct> findSuggestProduct(SuggestCommand command) {

        List<Basket> basketList = getBasketPort.getBasket();

        if(basketList == null || basketList.size() == 0){
            return new ArrayList<>();
        }

        Set<Long> productIds = basketList.stream().map(Basket :: getProductId)
                .collect(Collectors.toSet());

        List<String> productBrandNames = getProductPort.getProductBrandName(new ArrayList<>(productIds));

        if(productBrandNames == null || productBrandNames.size() == 0){
            throw new ErrorException(SearchError.NO_EXIST_PRODUCT,"findSuggestProduct");
        }

        return findSuggestProductPort.findSuggestProduct(productBrandNames);
    }
}
