package org.example.adapter.out.persist;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Getter
@Document(indexName = "product-search")
public class SearchProduct {

    @Id
    private String id;
    @Field
    private final String version;
    @Field
    private final String message;
    @Field
    private final String productName;
    @Field
    private final int productId;
    @Field
    private final String brandName;
    @Field
    private final String type;

    @Field
    private final String productImage;

    @Builder
    public SearchProduct(String version, String message, String productName, int productId, String brandName, String type, String productImage) {
        this.version = version;
        this.message = message;
        this.productName = productName;
        this.productId = productId;
        this.brandName = brandName;
        this.productImage = productImage;
        this.type = type;
    }
}
