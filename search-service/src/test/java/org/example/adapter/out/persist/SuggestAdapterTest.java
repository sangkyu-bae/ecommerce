package org.example.adapter.out.persist;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.example.application.port.out.GetBasketPort;
import org.example.application.port.out.GetProductPort;
import org.example.application.service.FindSuggestService;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SuggestAdapterTest {


    @MockBean
    private GetProductPort getProductPort;
    @MockBean
    private GetBasketPort getBasketPort;
    @Autowired
    private FindSuggestService findSuggestService;

    @Autowired
    private SearchProductRepository searchProductRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Autowired
    private RestHighLevelClient client;

    @AfterEach
    void tearDown() {
        searchProductRepository.deleteAll();
    }

    private SearchProduct create(int productId,String brandName){
        return SearchProduct.builder()
                .version("1.0")  // version 필드 초기화
                .message("Product message")  // message 필드 초기화
                .productId(productId)
                .productName(brandName + productId)
                .brandName(brandName)
                .type("운동화")
                .build();
    }

    private HashMap<String,Object> createMap(int productId, String brandName){

        HashMap<String, Object> product = new HashMap<>();

        product.put("productId", productId);
        product.put("brandName", brandName);
        product.put("type","운동화");
        product.put("productName",  brandName + productId);

        return product;
    }

    public String insertData(Map<String, Object> data) {
        String productIndex = "product-search";
        try {
            IndexRequest request = new IndexRequest(productIndex)
                    .id(null)
                    .source(data, XContentType.JSON);
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            return response.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}