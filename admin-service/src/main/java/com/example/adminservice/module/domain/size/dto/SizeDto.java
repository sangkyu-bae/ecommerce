package com.example.adminservice.module.domain.size.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SizeDto {

    private Long id;
    @NotNull
    private int size;
}
