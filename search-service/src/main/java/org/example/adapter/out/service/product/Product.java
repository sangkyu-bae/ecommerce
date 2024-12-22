package org.example.adapter.out.service.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Product {
    private Long id;

    private String name;

    private int price;

    private String description;

    private String productImage;

    private Brand brand;

    @Value
    @Getter
    class Brand{
        private Long id;

        private String name;
    }
}
