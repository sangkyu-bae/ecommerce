package org.example.adapter.out.persist;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.index.query.QueryBuilders;


import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.example.domain.TopProduct;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.example.PersistenceAdapter;
import org.example.application.port.out.FindSuggestProductPort;

import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;


import java.util.List;


@PersistenceAdapter
@Slf4j
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SuggestAdapter implements FindSuggestProductPort {

    private final ElasticsearchRestTemplate elasticsearchTemplate;

    @Override
    public List<TopProduct> findSuggestProduct(List<String> productNameList)  {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termsQuery("brandName.keyword", productNameList)) // 필터: 여러 브랜드
                .addAggregation(
                        AggregationBuilders.terms("popular_products")
                                .field("productName.keyword") // 집계: 상품 이름
                                .size(5)                     // 상위 5개만
                                .subAggregation(             // 각 버킷에 대해 top_hits 집계 추가
                                        AggregationBuilders.topHits("top_product")
                                                .size(1) // 각 그룹에 대해 하나의 상품만 반환
                                )
                )
                .withMaxResults(0) // 검색 결과 제외, 집계만 반환
                .build();
        SearchHits<SearchProduct> searchHits = elasticsearchTemplate.search(searchQuery, SearchProduct.class);

        AggregationMapper<SearchProduct, TopProduct> mapper = new AggregationMapper<>(new ObjectMapper());

        List<TopProduct> topProducts = mapper.map(
                searchHits,
                "popular_products",
                "top_product",
                TopProduct.class
        );

        return topProducts;
    }
}
