package com.example.adminservice.adapter.out.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
public class SendProductTask {

    private String productName;

    private String brandName;

    private long productId;

    private String type;

    private String productImage;

    @Builder
    public SendProductTask(String productName, String brandName, long productId, String type, String productImage) {
        this.productName = productName;
        this.brandName = brandName;
        this.productId = productId;
        this.type = type;
        this.productImage = productImage;
    }
}
