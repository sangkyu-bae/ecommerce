package com.example.adminservice.application.port.in.product;

import com.example.adminservice.adapter.in.web.request.productRequest.RegisterColorRequest;
import com.example.adminservice.common.SelfValidating;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Builder @Data @EqualsAndHashCode(callSuper = true)
@AllArgsConstructor @NoArgsConstructor
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
        this.category =category;
        this.validateSelf();
    }



}
