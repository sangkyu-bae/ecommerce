package com.example.delivery.adapter.in.web;

import com.example.delivery.adapter.in.request.UpdateDeliveryRequest;
import com.example.delivery.application.port.in.command.UpdateDeliveryCommand;
import com.example.delivery.application.port.in.usecase.UpdateDeliveryUseCase;
import com.example.delivery.domain.DeliveryVo;
import com.example.delivery.vaildator.UpdateDeliveryCommandValidator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class UpdateDeliveryController {

    private final UpdateDeliveryCommandValidator validator;

    private final UpdateDeliveryUseCase updateDeliveryUseCase;

    @Operation(summary = "update delivery", description = "배송상태 수정하기")
    @PutMapping("/delivery/{deliveryId}")
    public ResponseEntity<DeliveryVo> updateDeliveryVo(@RequestBody UpdateDeliveryRequest request,
                                       @RequestParam("deliveryId") long deliveryId){

        UpdateDeliveryCommand command = UpdateDeliveryCommand.builder()
                .status(request.getStatus())
                .deliveryId(deliveryId)
                .build();
        Errors errors = new BeanPropertyBindingResult(command,"command");
        validator.validate(command,errors);
        if(errors.hasErrors()){
//            throw new ErrorException(OrderErrorCode.ORDER_NO_VALIDATE,"removeOrder");
        }

        DeliveryVo deliveryVo = updateDeliveryUseCase.updateDelivery(command);

        return ResponseEntity.ok().body(deliveryVo);
    }
}
