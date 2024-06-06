package org.example.basket.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.example.PersistenceAdapter;
import org.example.basket.adapter.out.persistence.entity.BasketEntity;
import org.example.basket.adapter.out.persistence.repository.BasketRepository;
import org.example.basket.application.port.out.FindBasketPort;
import org.example.basket.application.port.out.RegisterBasketPort;
import org.example.basket.application.port.out.RemoveBasketPort;
import org.example.basket.domain.Basket;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class BasketPersistenceAdapter implements RegisterBasketPort, RemoveBasketPort, FindBasketPort {

    private final BasketRepository basketRepository;

    @Override
    public BasketEntity registerBasket(Basket basket) {

        BasketEntity registerEntity = BasketEntity.builder()
                .productQuantity(basket.getProductQuantity())
                .memberId(basket.getMemberId())
                .productSizeId(basket.getProductSizeId())
                .status(basket.getStatus())
                .createAt(basket.getCreatAt())
                .updateAt(basket.getUpdateAt())
                .build();

        return basketRepository.save(registerEntity);
    }

    @Override
    public List<BasketEntity> registerBaskets(List<Basket> baskets) {
        List<BasketEntity> registerBasketEntityList = baskets.stream().map(basket-> BasketEntity.builder()
                .productQuantity(basket.getProductQuantity())
                .memberId(basket.getMemberId())
                .productSizeId(basket.getProductSizeId())
                .status(basket.getStatus())
                .createAt(basket.getCreatAt())
                .updateAt(basket.getUpdateAt())
                .build())
                .collect(Collectors.toList());

        return basketRepository.saveAll(registerBasketEntityList);
    }

    @Override
    public void removeBasket(Basket.BasketId basketId) {
        basketRepository.deleteById(basketId.getId());
    }

    @Override
    public List<BasketEntity> findBasketList(Basket.BasketMemberId basketMemberId) {
        return basketRepository.findByMemberIdOrderByCreateAtDesc(basketMemberId.getMemberId());
    }
}
