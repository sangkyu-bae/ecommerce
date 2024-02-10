package org.example.ranking.adapter.out.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.security.DenyAll;

@Data
@NoArgsConstructor
@DenyAll
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private Long id;

    private String name;
}
