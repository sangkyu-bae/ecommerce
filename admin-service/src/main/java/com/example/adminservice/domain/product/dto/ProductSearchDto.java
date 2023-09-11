package com.example.adminservice.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductSearchDto  {

    List<ResponseProductDto> productList;

    private int pageNumber;

    private int pageSize;

    private long totalElements;

}
