package org.example.ranking.domain;

import lombok.*;

@Getter
@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class RedisRanking{

    private long productId;

    private Long score;

    public static RedisRanking createGenerateRedisRanking(
            RedisRankingProductId rankingProductId,
            RedisRankingScore redisRankingScore
    ){
        return new RedisRanking(
                rankingProductId.productId,
                redisRankingScore.score
        );
    }

    public static class RedisRankingProductId{
        public RedisRankingProductId(long value){
            this.productId = value;
        }

        private long productId;
    }
    public static class RedisRankingScore{
        public RedisRankingScore(Long value){
            this.score = value;
        }

        private Long score;
    }
}
