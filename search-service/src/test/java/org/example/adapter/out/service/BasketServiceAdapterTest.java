package org.example.adapter.out.service;

import org.example.adapter.out.service.basket.Basket;
import org.example.adapter.out.service.basket.BasketServiceAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
class BasketServiceAdapterTest {


    @Mock
    private BasketServiceAdapter basketServiceAdapter;

    @DisplayName("유저의 장바구니 목록을 조회한다.")
    @Test
    void getBasket(){
        List<Basket> basketList = new ArrayList<>();
        for(int i = 1; i <= 3 ;i++){
            basketList.add(createBasket(i));
        }

        when(basketServiceAdapter.getBasket())
                .thenReturn(basketList);

    }


    private Basket createBasket(long productSizeId){

        return Basket.builder()
                .id(1L)
                .productSizeId(productSizeId)
                .build();
    }

}