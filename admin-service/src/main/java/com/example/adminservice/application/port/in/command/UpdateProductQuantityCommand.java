package com.example.adminservice.application.port.in.command;

import lombok.*;
import org.example.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Builder @Data @AllArgsConstructor @NoArgsConstructor
public class UpdateProductQuantityCommand extends SelfValidating<UpdateProductQuantityCommand> {
    @NotBlank
    @NotNull
    private long sizeId;

    @NotBlank
    @NotNull
    private int amount;
}
