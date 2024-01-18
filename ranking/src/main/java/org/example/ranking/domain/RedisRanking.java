package org.example.ranking.domain;

import lombok.*;

@Getter
@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class RedisRanking{

    private long productId;

    private String productName;

    private Long score;

    public static RedisRanking createGenerateRedisRanking(
            RedisRankingProductId rankingProductId,
            RedisRankingProductName rankingProductName,
            RedisRankingScore redisRankingScore
    ){
        return new RedisRanking(
                rankingProductId.getProductId(),
                rankingProductName.getProductName(),
                redisRankingScore.getScore()
        );
    }

    @Value
    public static class RedisRankingProductId{
        public RedisRankingProductId(long value){
            this.productId = value;
        }

        private long productId;
    }

    @Value
    public static class RedisRankingProductName{
        public RedisRankingProductName(String value){
            this.productName = value;
        }

        private String productName;
    }

    @Value
    public static class RedisRankingScore{
        public RedisRankingScore(Long value){
            this.score = value;
        }

        private Long score;
    }
}
