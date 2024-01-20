package org.example.basket.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.basket.adapter.out.persistence.BasketMapper;
import org.example.basket.adapter.out.persistence.entity.BasketEntity;
import org.example.basket.adapter.out.service.Product;
import org.example.basket.application.port.in.command.RegisterBasketCommand;
import org.example.basket.application.port.in.usecase.RegisterBasketUseCase;
import org.example.basket.application.port.out.GetProductPort;
import org.example.basket.application.port.out.RegisterBasketPort;
import org.example.basket.domain.Basket;
import org.example.basket.infra.error.BasketErrorCode;
import org.example.basket.infra.error.ErrorException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@UseCase
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RegisterBasketService implements RegisterBasketUseCase {

    private final RegisterBasketPort registerBasketPort;

    private final BasketMapper basketMapper;

    private final GetProductPort getProductPort;

    @Override
    public Basket RegisterBasket(RegisterBasketCommand command) {

        boolean existProduct = getProductPort.getProduct(command.getProductSizeId());

        if(!existProduct){
            throw new ErrorException(BasketErrorCode.PRODUCT_NOT_FOUND,"RegisterBasket");
        }

        Basket registerBasket = Basket.createGenerateBasket(
                new Basket.BasketId(null),
                new Basket.BasketMemberId(command.getMemberId()),
                new Basket.BasketProductSizeId(command.getProductSizeId()),
                new Basket.BasketProductQuantity(command.getQuantity()),
                Basket.BasketStatus.CREATE,
                new Basket.BasketCreateAt(LocalDateTime.now()),
                new Basket.BasketUpdateAt(LocalDateTime.now())
        );

        BasketEntity registerEntity = registerBasketPort.registerBasket(registerBasket);

        return basketMapper.mapToDomainEntity(registerEntity);
    }
}
