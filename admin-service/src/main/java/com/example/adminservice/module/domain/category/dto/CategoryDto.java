package com.example.adminservice.module.domain.category.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryDto {

    private Long id;
    @NotBlank
    @Length(min = 2,max = 30)
    private String name;

}
