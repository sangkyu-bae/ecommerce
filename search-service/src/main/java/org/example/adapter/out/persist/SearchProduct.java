package org.example.adapter.out.persist;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchProduct {

    private String version;
    private String message;
    private String productName;
    private int productId;
    private String brandName;
    private String type;
}
