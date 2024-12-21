package org.example.application.service;

import org.example.adapter.out.service.basket.Basket;
import org.example.adapter.out.service.basket.BasketServiceAdapter;
import org.example.adapter.out.service.product.Product;
import org.example.adapter.out.service.product.ProductServiceAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class FindSuggestServiceTest {


    @Mock
    private BasketServiceAdapter basketServiceAdapter;

    @Mock
    private ProductServiceAdapter productServiceAdapter;

    @Mock
    private FindSuggestService findSuggestService;


    @DisplayName("유저의 장바구니 목록을 조회하여, 상품명 기반으로 상품을 추천한다.")
    @Test
    void findSuggestProduct() {

        List<Basket> basketList = new ArrayList<>();
        for(int i = 1; i <= 3 ;i++){
            basketList.add(createBasket(i));
        }

        when(basketServiceAdapter.getBasket())
                .thenReturn(basketList);
        when(productServiceAdapter.getProductName(Arrays.asList(1L,2L,3L)))
                .thenReturn(Arrays.asList("nike 신발","adidas 신발"));


        when(findSuggestService.findSuggestProduct(any()))
                .thenReturn(Map.of("test","test"));


    }


    private Basket createBasket(long productSizeId){

        return Basket.builder()
                .id(1L)
                .productSizeId(productSizeId)
                .build();
    }
}