package com.example.adminservice.module.domain.product.dto;

import com.example.adminservice.module.domain.size.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class SizeAndQuantityDto {
//    private int size;
    private Size size;
    private int quantity;
}
