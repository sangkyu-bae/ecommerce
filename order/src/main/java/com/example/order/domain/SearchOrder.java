package com.example.order.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SearchOrder {

    private final List<OrderAggregationVo> orderAggregationVos;

    private final int pageNumber;

    private final int pageSize;

    private final long totalElement;

    private final int totalPage;

    public static SearchOrder createGenerate(
            List<OrderAggregationVo> orderAggregationVos,
            int pageNumber,
            int pageSize,
            long totalElement,
            int totalPage
    ){
        return new SearchOrder(orderAggregationVos, pageNumber, pageSize, totalElement,totalPage);
    }
}
