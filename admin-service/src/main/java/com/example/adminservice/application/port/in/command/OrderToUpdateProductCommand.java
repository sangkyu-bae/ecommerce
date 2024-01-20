package com.example.adminservice.application.port.in.command;


import lombok.*;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;

@Data
@Builder @EqualsAndHashCode(callSuper = true)
@AllArgsConstructor @NoArgsConstructor
public class OrderToUpdateProductCommand extends SelfValidating<OrderToUpdateProductCommand> {

    @NotNull
    private int amount;

    @NotNull
    private Long sizeId;

}
