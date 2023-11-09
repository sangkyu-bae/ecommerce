package com.example.delivery.application.port.in.command;


import lombok.*;
import org.example.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(callSuper = true)
@Builder
public class RegisterDeliveryCommand extends SelfValidating<RegisterDeliveryCommand> {

    @NotNull
    private Long sizeId;

    @NotNull
    @NotBlank
    private String userId;

    @NotNull
    @NotBlank
    private String address;

    private long orderId;
}
