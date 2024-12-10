package com.example.adminservice.adapter.out.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
public class SendProductTask {

    private String productName;

    private String brandName;

    private long productId;

    private String type;

}
