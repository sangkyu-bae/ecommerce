package org.example.basket.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.basket.adapter.out.persistence.BasketMapper;
import org.example.basket.adapter.out.persistence.entity.BasketEntity;
import org.example.basket.adapter.out.service.Product;
import org.example.basket.application.port.in.command.FindBasketCommand;
import org.example.basket.application.port.in.usecase.FindBasketUseCase;
import org.example.basket.application.port.out.FindBasketPort;
import org.example.basket.application.port.out.GetProductPort;
import org.example.basket.domain.Basket;
import org.example.basket.domain.BasketAggregationVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UseCase
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class FindBasketService implements FindBasketUseCase {

    private final FindBasketPort findBasketPort;

    private final BasketMapper basketMapper;

    private final GetProductPort getProductPort;
    @Override
    public List<Basket> findBasketList(FindBasketCommand command) {
        Basket.BasketMemberId basketMemberId = new Basket.BasketMemberId(command.getMemberId());
        List<BasketEntity> basketEntityList = findBasketPort.findBasketList(basketMemberId);

        return basketEntityList.stream()
                .map(basketEntity -> basketMapper.mapToDomainEntity(basketEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<BasketAggregationVo> findBasketAggregationList(FindBasketCommand command) {
        Basket.BasketMemberId basketMemberId = new Basket.BasketMemberId(command.getMemberId());
        List<BasketEntity> basketEntityList = findBasketPort.findBasketList(basketMemberId);
        Set<Long> productIdsSet = basketEntityList.stream()
                .map(BasketEntity::getProductId) // 메서드 참조 사용
                .collect(Collectors.toSet());

        List<Long> productIds = new ArrayList<>(productIdsSet);
        List<Product> productList = getProductPort.getProductListByProductIds(productIds);

        List<BasketAggregationVo> basketAggregationVos = new ArrayList<>();

        /**
         * ToDo 구현 필요
         * */
        for(BasketEntity basketEntity : basketEntityList){
//            Basket.BasketSize
        }

        return null;
    }
}
