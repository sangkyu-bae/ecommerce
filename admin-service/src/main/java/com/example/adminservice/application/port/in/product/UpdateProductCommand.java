package com.example.adminservice.application.port.in.product;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor
public class UpdateProductCommand extends RegisterProductCommand {
    private Long id;

    @Builder(builderMethodName = "updateProductCommandBuilder")
    public UpdateProductCommand(Long id, String name, int price, String description, String productImage, RegisterBrandCommand brand, RegisterCategoryCommand category, Set<RegisterProductComponentCommand> productComponents) {
        super(name, price, description, productImage, brand, category, productComponents);
        this.id = id;
    }
}
