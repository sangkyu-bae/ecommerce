package org.example.ranking.adapter.out.persistance;

import lombok.*;

@Getter
@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class RedisRanking {

    private String productName;

    private long score;

    public RedisRanking createGenerateRedisRanking(
            RedisRankingProductName rankingProductName,
            RedisRankingScore redisRankingScore
    ){
        return new RedisRanking(
                rankingProductName.productName,
                redisRankingScore.score
        );
    }

    public static class RedisRankingProductName{
        public RedisRankingProductName(String value){
            this.productName = value;
        }

        private String productName;
    }

    public static class RedisRankingScore{
        public RedisRankingScore(long value){
            this.score = value;
        }

        private long score;
    }
}
