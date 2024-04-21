package com.example.adminservice.application.port.in.command;

import com.example.adminservice.adapter.in.request.RegisterProductRequest;

import lombok.*;
import org.example.SelfValidating;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
@EqualsAndHashCode(callSuper = true)
@Builder @Data
@AllArgsConstructor @NoArgsConstructor @Getter
public class RegisterProductCommand extends SelfValidating<RegisterProductCommand> {

    @NotBlank
    @Length(min = 2, max = 30)
    private String name;

    @NotNull
    private int price;

    @NotBlank
    @Length(min = 15)
    private String description;

    private String productImage;

    @NotNull
    private RegisterBrandCommand brand;

    @NotNull
    private RegisterCategoryCommand category;

    @NotNull
    private Set<RegisterProductComponentCommand> productComponents;


    public RegisterProductCommand(
            RegisterBrandCommand brand,
            RegisterCategoryCommand category,
            Set<RegisterProductComponentCommand> productComponents,
            String name,
            int price,
            String description,
            String productImage
    ) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.productImage = productImage;
        this.brand = brand;
        this.productComponents = productComponents;
        this.category = category;
        this.validateSelf();
    }

    public static RegisterProductCommand createProductCommand(RegisterProductRequest registerProductRequest,
                                                              RegisterBrandCommand registerBrandCommand,
                                                              RegisterCategoryCommand registerCategoryCommand,
                                                              Set<RegisterProductComponentCommand> componentCommands
                                                              ){
        return new RegisterProductCommand(
                registerBrandCommand,
                registerCategoryCommand,
                componentCommands,
                registerProductRequest.getName(),
                registerProductRequest.getPrice(),
                registerProductRequest.getDescription(),
                registerProductRequest.getProductImage()
        );
    }

}
