package com.example.adminservice.application.port.in.product;

import com.example.adminservice.common.SelfValidating;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder @EqualsAndHashCode(callSuper = true)
@AllArgsConstructor @NoArgsConstructor
public class CreateOrderToUpdateProductCommand extends SelfValidating<CreateOrderToUpdateProductCommand> {

    @NotNull
    private int amount;

    @NotNull
    private Long sizeId;

    @NotNull
    private Long orderId;

    @NotNull
    @NotBlank
    private String address;
}
