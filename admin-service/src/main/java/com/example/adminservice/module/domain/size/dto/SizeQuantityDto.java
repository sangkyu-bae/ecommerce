package com.example.adminservice.module.domain.size.dto;

import com.example.adminservice.module.domain.quantity.entity.Quantity;
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
