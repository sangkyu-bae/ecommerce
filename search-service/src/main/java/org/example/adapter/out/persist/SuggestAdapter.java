package org.example.adapter.out.persist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;

import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;

import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.TopHitsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.example.PersistenceAdapter;
import org.example.application.port.out.FindSuggestProductPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.AggregationsContainer;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
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
    public List<String> findSuggestProduct(List<String> productNameList)  {
        String[] indices = new String[] {"product-click-log-2024.12.22"};
        // NativeSearchQuery 설정
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
                .build();

        // SearchRequest 생성 시 인덱스를 지정
        SearchRequest searchRequest = new SearchRequest(indices);  // 인덱스명 설정
        searchRequest.source(searchQuery+.getQuery());  // 쿼리 설정
        searchRequest.source().aggregation("popular_products", searchQuery.getAggregations());  // 집계 설정

        // ElasticsearchRestTemplate을 사용하여 쿼리 실행
        SearchHits<SearchProduct> searchHits = elasticsearchTemplate.search(searchRequest, SearchProduct.class);

        // SearchSourceBuilder 생성
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        AggregationBuilder aggregation = AggregationBuilders.terms("agg_name")
//                .field("field_name");
//        searchSourceBuilder.query(searchQuery.getQuery());  // 쿼리 설정
//        searchSourceBuilder.aggregation(aggregation);  // 집계 설정
//        searchSourceBuilder.size(0);  // 페이지 크기 0으로 설정하여 결과는 반환하지 않음
//
//        // SearchRequest 생성 시 인덱스를 지정
//        SearchRequest searchRequest = new SearchRequest(indices); // 인덱스명 설정
//        searchRequest.source(searchSourceBuilder);  // SearchSourceBuilder 설정
//
//
//
//
//        SearchHits<SearchProduct> searchHits = elasticsearchTemplate.search((Query) searchRequest,SearchProduct.class);

// 3. Parse the aggregation results
        AggregationsContainer<?> aggregationsContainer = searchHits.getAggregations();
        if (aggregationsContainer != null) {
            Aggregations aggregations = (Aggregations) aggregationsContainer;  // 적절히 형변환

            Terms popularProductsTerms = aggregations.get("popular_products");
            List<String> popularProducts = popularProductsTerms.getBuckets()
                    .stream()
                    .map(bucket -> bucket.getKeyAsString()) // 상품 이름 추출
                    .collect(Collectors.toList());

            return popularProducts;
        } else {
            // Aggregations가 null인 경우 처리
            return Collections.emptyList();
        }

    }
}
