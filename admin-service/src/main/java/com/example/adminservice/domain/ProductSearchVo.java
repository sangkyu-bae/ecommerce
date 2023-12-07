package com.example.adminservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ProductSearchVo {

    private final List<ProductVo> productVoList;

    private final int pageNumber;

    private final int pageSize;

    private final long totalElement;

    public static ProductSearchVo createGenerateProductSearchVo(
            ProductList productList,
            PageNumber pageNumber,
            PageSize pageSize,
            TotalElement pageTotalElement
    ){
        return new ProductSearchVo(
                productList.getProductList(),
                pageNumber.getPageNumber(),
                pageSize.getPageSize(),
                pageTotalElement.getTotalElement()
        );
    }

    @Value
    public static class PageNumber {
        public PageNumber(int value){
            this.pageNumber = value;
        }
        int pageNumber;
    }

    @Value
    public static class PageSize{
        public PageSize(int value){
            this.pageSize = value;
        }

        int pageSize;
    }

    @Value
    public static class TotalElement{
        public TotalElement(long value){
            this.totalElement = value;
        }
        long totalElement;
    }

    @Value
    public static class ProductList{
        public ProductList(List<ProductVo> value){
            this.productList = value;
        }
        List<ProductVo> productList;
    }
}
