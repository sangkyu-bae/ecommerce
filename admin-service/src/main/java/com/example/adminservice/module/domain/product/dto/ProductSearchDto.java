package com.example.adminservice.module.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Pageable;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ProductSearchDto extends ResponseProductDto{
    int page;

    public ProductSearchDto(Pageable pageable){
        this.page = pageable.getNumberOfPages();
    }

}
