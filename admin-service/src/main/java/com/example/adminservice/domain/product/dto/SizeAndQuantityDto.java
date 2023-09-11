package com.example.adminservice.domain.product.dto;

import com.example.adminservice.domain.size.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class SizeAndQuantityDto {
    private Size sizeDto;
    private int quantity;
}
