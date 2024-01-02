package com.example.delivery.application.port.in.command;


import com.example.delivery.adapter.in.request.UpdateDeliveryRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor @Builder @EqualsAndHashCode(callSuper = true)
public class UpdateDeliveryCommand extends SelfValidating<UpdateDeliveryCommand> {


    @NotNull
    private long deliveryId;
    @NotNull
    private int status;

    public UpdateDeliveryCommand(long deliveryId, int status){
        this.status = status;
        this.deliveryId = deliveryId;
        this.validateSelf();
    }
}
