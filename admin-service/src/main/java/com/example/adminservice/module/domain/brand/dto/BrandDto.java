package com.example.adminservice.module.domain.brand.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class BrandDto {

    private Long id;
    @NotBlank
    @Length(min = 2, max = 20)
    private String name;

    private String brandImage;
}
