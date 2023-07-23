package com.example.adminservice.module.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
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
