package org.example.ranking.adapter.out.persistance.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter @Setter
@RedisHash("rankingView")
@AllArgsConstructor
@NoArgsConstructor @Builder
public class RedisRankingEntity {

    @Id
    private String id;

    private String productName;

    private long productId;

    private int viewCount;

    private int saleCount;
}
