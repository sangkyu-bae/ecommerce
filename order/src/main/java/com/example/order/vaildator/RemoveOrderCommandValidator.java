package com.example.order.vaildator;

import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.adapter.out.persistence.repository.OrderEntityRepository;
import com.example.order.application.port.in.command.RemoveOrderCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class RemoveOrderCommandValidator implements Validator {

    private final OrderEntityRepository orderEntityRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RemoveOrderCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RemoveOrderCommand command = (RemoveOrderCommand) target;

        OrderEntity orderEntity = orderEntityRepository.findById(command.getOrderId())
                .orElseThrow();

        if (orderEntity.getUserId() != command.getUserId()) {
            errors.rejectValue("userId", "no authority");
        }
    }
}
