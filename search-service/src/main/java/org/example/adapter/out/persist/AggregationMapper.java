package org.example.adapter.out.persist;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.TopHits;
import org.example.domain.TopProduct;
import org.springframework.data.elasticsearch.core.AggregationsContainer;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public  class AggregationMapper <T,M>{
    private final ObjectMapper objectMapper;

    public AggregationMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<M> map(SearchHits<T> searchHits, String aggregationName, String subAggregation , Class<M> targetType) {
        AggregationsContainer<?> aggregationsContainer = searchHits.getAggregations();
        Aggregations aggregations = (Aggregations) aggregationsContainer.aggregations();

        Terms terms = aggregations.get(aggregationName);
        if (terms == null) {
            //에러 공통화 필요
            throw new IllegalArgumentException("Aggregation not found: " + aggregationName);
        }

        List<M> results = new ArrayList<>();
        for (Terms.Bucket bucket : terms.getBuckets()) {

            TopHits topHits = bucket.getAggregations().get(subAggregation);
            if (topHits != null) {
                topHits.getHits().forEach(hit -> {
                    try {
                        results.add(objectMapper.readValue(hit.getSourceAsString(), targetType));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

        }

        return results;
    }

}
