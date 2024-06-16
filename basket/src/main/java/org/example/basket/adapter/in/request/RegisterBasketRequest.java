package org.example.basket.adapter.in.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterBasketRequest {

    private long productSizeId;

    private int quantity;

    private int size;

    private long productId;

}
