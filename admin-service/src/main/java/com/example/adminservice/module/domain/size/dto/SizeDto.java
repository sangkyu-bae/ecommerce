package com.example.adminservice.module.domain.size.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SizeDto {

    @NotNull
    int size;
}
