package org.example.basket.adapter.out.persistence;

import org.example.basket.adapter.out.persistence.entity.BasketEntity;
import org.example.basket.domain.Basket;
import org.springframework.stereotype.Component;

@Component
public class BasketMapper {

    public Basket mapToDomainEntity(BasketEntity basketEntity){

        return Basket.createGenerateBasket(
                new Basket.BasketId(basketEntity.getId()),
                new Basket.BasketMemberId(basketEntity.getMemberId()),
                new Basket.BasketProductSizeId(basketEntity.getProductSizeId()),
                new Basket.BasketProductQuantity(basketEntity.getProductQuantity()),
                Basket.BasketStatus.findStatus(basketEntity.getStatus()),
                new Basket.BasketProductId(basketEntity.getProductId()),
                new Basket.BasketSize(basketEntity.getSize()),
                new Basket.BasketColorName(basketEntity.getColorName()),
                new Basket.BasketCreateAt(basketEntity.getCreateAt()),
                new Basket.BasketUpdateAt(basketEntity.getUpdateAt())
        );
    }
}
