package com.example.adminservice.application.port.in.command;

import lombok.*;
import org.example.SelfValidating;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder  @NoArgsConstructor
public class FindProductByProductIdsCommand extends SelfValidating<FindProductByProductIdsCommand> {

    @NotNull
    private List<Long> productIds;

    public FindProductByProductIdsCommand(List<Long> productIds){
        this.productIds = productIds;
        this.validateSelf();
    }
}
