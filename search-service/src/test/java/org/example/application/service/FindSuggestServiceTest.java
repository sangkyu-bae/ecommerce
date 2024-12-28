package org.example.application.service;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.example.adapter.out.persist.SearchProduct;
import org.example.adapter.out.persist.SearchProductRepository;
import org.example.adapter.out.service.basket.Basket;
import org.example.application.port.in.command.SuggestCommand;
import org.example.application.port.out.GetBasketPort;
import org.example.application.port.out.GetProductPort;
import org.example.domain.TopProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@ActiveProfiles("test")
class FindSuggestServiceTest {

    @MockBean
    private GetProductPort getProductPort;
    @MockBean
    private  GetBasketPort getBasketPort;
    @Autowired
    private FindSuggestService findSuggestService;

    @Autowired
    private SearchProductRepository searchProductRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Autowired
    private RestHighLevelClient client;

    @BeforeEach
    void insert(){

    }
    @AfterEach
    void tearDown() {
        searchProductRepository.deleteAll();
    }


    @DisplayName("유저의 장바구니 목록을 조회하여, 브랜드명 기반으로 상품을 추천한다.")
    @Test
    void findSuggestProduct() {
       //given
        List<Basket> basketList = new ArrayList<>();
        for(int i = 1; i <= 3 ;i++){
            basketList.add(createBasket(i));
        }
        when(getBasketPort.getBasket()).thenReturn(basketList);

        List<String> productBrandNames = List.of("adidas");
        when(getProductPort.getProductBrandName(anyList())).thenReturn(productBrandNames);

        SuggestCommand suggestCommand = new SuggestCommand(1L);

        List<HashMap<String,Object>> searchProducts = new ArrayList<>();
        searchProducts.add(createMap(1,"adidas"));
        searchProducts.add(createMap(3,"adidas"));
        searchProducts.add(createMap(3,"adidas"));
        searchProducts.add(createMap(2,"adidas"));
        searchProducts.add(createMap(7,"nike"));
        searchProducts.add(createMap(7,"nike"));

        for(HashMap<String,Object> stringObjectHashMap : searchProducts){
            insertData(stringObjectHashMap);
        }


        // when: findSuggestProduct 메서드 호출
        List<TopProduct> res = findSuggestService.findSuggestProduct(suggestCommand);

        //then
        assertThat(res).hasSize(3)
                .extracting("brandName","productName")
                .containsExactlyInAnyOrder(
                        tuple("adidas", "adidas1"),
                        tuple("adidas", "adidas3"),
                        tuple("adidas", "adidas2")
                );
    }


    private Basket createBasket(long productSizeId){

        return Basket.builder()
                .id(1L)
                .productSizeId(productSizeId)
                .build();
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