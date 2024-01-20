package org.example.basket.vaildator;


import lombok.RequiredArgsConstructor;
import org.example.basket.adapter.out.persistence.entity.BasketEntity;
import org.example.basket.adapter.out.persistence.repository.BasketRepository;
import org.example.basket.application.port.in.command.RemoveBasketCommand;
import org.example.basket.infra.error.BasketErrorCode;
import org.example.basket.infra.error.ErrorException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
@RequiredArgsConstructor
public class RemoveBasketCommandValidator implements Validator {

    private final BasketRepository basketRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RemoveBasketCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RemoveBasketCommand command = (RemoveBasketCommand) target;

        BasketEntity basketEntity = basketRepository.findById(command.getBasketId())
                .orElseThrow(()-> new ErrorException(BasketErrorCode.BASKET_NOT_FOUND,"remove Basket"));

        if (basketEntity.getMemberId() != command.getMemberId()) {
            errors.rejectValue("userId", "no authority");
        }

    }
}
