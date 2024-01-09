package org.example.ranking.domain;

import lombok.*;

@Getter
@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class RedisRanking{

    private String productName;

    private long productId;

    private Long score;

    public static RedisRanking createGenerateRedisRanking(
            RedisRankingProductName rankingProductName,
            RedisRankingProductId rankingProductId,
            RedisRankingScore redisRankingScore
    ){
        return new RedisRanking(
                rankingProductName.productName,
                rankingProductId.productId,
                redisRankingScore.score
        );
    }

    public static class RedisRankingProductName{
        public RedisRankingProductName(String value){
            this.productName = value;
        }

        private String productName;
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
