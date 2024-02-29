package com.example.adminservice.adapter.out.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateRankingTask {
    private String productName;

    private long productId;
}
