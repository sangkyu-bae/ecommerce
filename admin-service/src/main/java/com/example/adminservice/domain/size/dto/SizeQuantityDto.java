package com.example.adminservice.domain.size.dto;

import lombok.Data;

@Data
public class SizeQuantityDto {

    private SizeDto sizeDto;

    private int quantity;

    public SizeQuantityDto(SizeDto sizeDto,int quantity){
        this.sizeDto = sizeDto;
        this.quantity = quantity;
    }
}
