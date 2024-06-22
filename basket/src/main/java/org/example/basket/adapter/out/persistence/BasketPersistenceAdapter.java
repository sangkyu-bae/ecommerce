package org.example.basket.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.example.PersistenceAdapter;
import org.example.basket.adapter.out.persistence.entity.BasketEntity;
import org.example.basket.adapter.out.persistence.repository.BasketRepository;
import org.example.basket.application.port.out.FindBasketPort;
import org.example.basket.application.port.out.RegisterBasketPort;
import org.example.basket.application.port.out.RemoveBasketPort;
import org.example.basket.application.port.out.UpdateBasketPort;
import org.example.basket.domain.Basket;
import org.example.basket.infra.error.BasketErrorCode;
import org.example.basket.infra.error.ErrorException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class BasketPersistenceAdapter implements RegisterBasketPort, RemoveBasketPort, FindBasketPort, UpdateBasketPort {

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
        List<BasketEntity> registerBasketEntityList = baskets.stream().map(basket -> BasketEntity.builder()
                        .productQuantity(basket.getProductQuantity())
                        .memberId(basket.getMemberId())
                        .productSizeId(basket.getProductSizeId())
                        .status(basket.getStatus())
                        .createAt(basket.getCreatAt())
                        .updateAt(basket.getUpdateAt())
                        .productId(basket.getProductId())
                        .size(basket.getSize())
                        .colorName(basket.getColorName())
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

    @Override
    public List<BasketEntity> findBasketListByMemberIdAndProductSizeIds(long memberId, Set<Long> productSizeIds) {
        return basketRepository.findByMemberIdAndProductSizeIdIn(memberId,productSizeIds);
    }

    @Override
    public BasketEntity updateBasketByQuantity(Basket.BasketId basketId, Basket.BasketProductQuantity quantity, Basket.BasketMemberId basketMemberId) {
        BasketEntity findBasketEntity = basketRepository.findById(basketId.getId())
                .orElseThrow(()->new ErrorException(BasketErrorCode.BASKET_NOT_FOUND,"updateBAsketByQuantity"));
        findBasketEntity.updateQuantity(quantity.getQuantity(), basketMemberId.getMemberId());

        return basketRepository.save(findBasketEntity);
    }
}
