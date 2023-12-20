package com.example.adminservice.adapter.axon.command;


import com.example.adminservice.adapter.axon.event.ProductCreateEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.SelfValidating;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor @Builder
public class ProductCreateCommand extends SelfValidating<ProductCreateCommand> {
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
    private String aggregateIdentifier;

    public ProductCreateCommand(String name, int price, String description,String productImage,String aggregateIdentifier){
        this.name = name;
        this.price = price;
        this.description = description;
        this.productImage = productImage;
        this.aggregateIdentifier = aggregateIdentifier;
        this.validateSelf();
    }
}
