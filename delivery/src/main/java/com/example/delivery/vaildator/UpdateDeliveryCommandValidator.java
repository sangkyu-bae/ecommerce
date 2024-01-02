package com.example.delivery.vaildator;

import com.example.delivery.adapter.out.persistance.repository.DeliveryEntityRepository;
import com.example.delivery.application.port.in.command.UpdateDeliveryCommand;
import com.example.delivery.domain.DeliveryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateDeliveryCommandValidator implements Validator {

    private final DeliveryEntityRepository deliveryEntityRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UpdateDeliveryCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UpdateDeliveryCommand command = (UpdateDeliveryCommand) target;

        if(!deliveryEntityRepository.existsById(command.getDeliveryId())){
            errors.rejectValue("deliveryId", "not exist delivery");
        }
        try{
            DeliveryVo.StatusCode.findStatusCode(command.getStatus());
        }catch (Exception e){
            errors.rejectValue("status", "no valid status");
        }

    }
}
