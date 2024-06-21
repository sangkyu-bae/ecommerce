package org.example.basket.adapter.out.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private Long id;

    private String name;

    private int price;

    private String description;

    private String productImage;

    private String aggregateIdentifier;

    private Brand brand;

    @Data
    @AllArgsConstructor @NoArgsConstructor
    static public class Brand{
        Long id;
        String name;
    }

}
