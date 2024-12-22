package org.example.application.service;

import org.example.adapter.out.service.basket.Basket;
import org.example.adapter.out.service.basket.BasketServiceAdapter;
import org.example.adapter.out.service.product.ProductServiceAdapter;
import org.example.application.port.in.command.SuggestCommand;
import org.example.application.port.out.GetBasketPort;
import org.example.application.port.out.GetProductPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class FindSuggestServiceTest {


    @Mock
    private BasketServiceAdapter basketServiceAdapter;

    @MockBean
    private GetProductPort getProductPort;
    @Mock
    private ProductServiceAdapter productServiceAdapter;
    @MockBean
    private  GetBasketPort getBasketPort;

    @Autowired
    private FindSuggestService findSuggestService;


    @DisplayName("유저의 장바구니 목록을 조회하여, 상품명 기반으로 상품을 추천한다.")
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

        // when: findSuggestProduct 메서드 호출
        Map<String, Object> result = findSuggestService.findSuggestProduct(suggestCommand);



        //then

    }


    private Basket createBasket(long productSizeId){

        return Basket.builder()
                .id(1L)
                .productSizeId(productSizeId)
                .build();
    }
}