package com.example.delivery.application.port.in.command;


import com.example.delivery.adapter.in.request.UpdateDeliveryRequest;
import com.example.delivery.domain.DeliveryVo;
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
    private int status;
    private String address;

    public UpdateDeliveryCommand(long deliveryId, int status, String address){
        this.status = status;
        this.deliveryId = deliveryId;
        this.address = address;
        this.validateSelf();
    }
}
