package com.example.adminservice.module.domain.color.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class ColorDto {

    @NotBlank
    @Length(min = 2,max = 20)
    private String name;
}
