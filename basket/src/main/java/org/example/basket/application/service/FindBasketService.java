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

import java.util.*;
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
                .map(BasketEntity::getProductId)
                .collect(Collectors.toSet());

        List<Long> productIds = new ArrayList<>(productIdsSet);
        List<Product> productList = getProductPort.getProductListByProductIds(productIds);

        List<BasketAggregationVo> basketAggregationVos = new ArrayList<>();

        Map<Long,Product> productMap = new HashMap<>();

        for(Product product: productList){
            productMap.put(product.getId(),product);
        }
        log.info("productMap Size: " + productMap.size());
        log.info(productMap.toString());

        for(BasketEntity basketEntity : basketEntityList){
            Product product = productMap.get(basketEntity.getProductId());
            basketAggregationVos.add(
                    BasketAggregationVo.createGenerate(
                            basketEntity.getId(),
                            basketEntity.getProductSizeId(),
                            basketEntity.getProductId(),
                            basketEntity.getSize(),
                            product.getPrice(),
                            basketEntity.getMemberId(),
                            basketEntity.getProductQuantity(),
                            basketEntity.getStatus(),
                            basketEntity.getCreateAt(),
                            basketEntity.getUpdateAt(),
                            product.getName()
                    )
            );
        }

        return basketAggregationVos;
    }
}
