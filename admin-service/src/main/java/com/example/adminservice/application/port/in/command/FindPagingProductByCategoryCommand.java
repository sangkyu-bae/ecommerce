package com.example.adminservice.application.port.in.command;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Builder
public class FindPagingProductByCategoryCommand extends SelfValidating<FindPagingProductCommand> {

    @NotNull
    private long categoryId;

    @NotNull
    private int pageNum;

    public FindPagingProductByCategoryCommand(long categoryId, int pageNum){
        this.categoryId = categoryId;
        this.pageNum = pageNum;
        this.validateSelf();
    }
}
