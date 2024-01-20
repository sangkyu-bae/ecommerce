package com.example.adminservice.application.port.in.command;

import lombok.*;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Builder @Data
@NoArgsConstructor
public class ExistProductCommand extends SelfValidating<ExistProductCommand> {

    @NotNull
    private long sizeId;

    public ExistProductCommand(long sizeId){
        this.sizeId = sizeId;
        this.validateSelf();
    }
}
