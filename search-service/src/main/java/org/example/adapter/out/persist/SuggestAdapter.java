package org.example.adapter.out.persist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.example.PersistenceAdapter;
import org.example.application.port.out.FindSuggestProductPort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@PersistenceAdapter
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SuggestAdapter implements FindSuggestProductPort {

    private final ElasticsearchRestTemplate elasticsearchTemplate;

    @Override
    public Map<String, Objects> findSuggestProduct(List<String> productNameList) {

        // 1. Build the query
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termsQuery("brandName.keyword", productNameList)) // 필터: 여러 브랜드
                .addAggregation(
                        AggregationBuilders.terms("popular_products")
                                .field("productName.keyword") // 집계: 상품 이름
                                .size(5)                     // 상위 5개만
                )
                .build();

// 2. Execute the query with the correct type
        SearchHits<Objects> searchHits = elasticsearchTemplate.search(searchQuery,Objects.class);

// 3. Parse the aggregation results
        Aggregations aggregations = searchHits.getAggregations();

        Terms popularProductsTerms = aggregations.get("popular_products");
        List<String> popularProducts = popularProductsTerms.getBuckets()
                .stream()
                .map(bucket -> bucket.getKeyAsString()) // 상품 이름 추출
                .collect(Collectors.toList());

        return popularProducts;

    }
}
