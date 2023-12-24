package com.example.order.vaildator;

import com.example.order.adapter.out.persistence.entity.OrderEntity;
import com.example.order.adapter.out.persistence.repository.OrderEntityRepository;
import com.example.order.application.port.in.command.RemoveOrderCommand;
import com.example.order.domain.OrderVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RemoveOrderCommandValidator implements Validator {

    private final OrderEntityRepository orderEntityRepository;

    private final List<Integer> ORDER_ERROR_STATUS_CODE = List.of(
            OrderVo.StatusCode.ORDER_REMOVE_SUCCESS.getStatus(),
            OrderVo.StatusCode.ORDER_REMOVE_FAIL.getStatus()
    );

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



        if(ORDER_ERROR_STATUS_CODE.contains(orderEntity.getStatus())){
            errors.rejectValue("statusCode","error Status Code");
        }
    }
}
