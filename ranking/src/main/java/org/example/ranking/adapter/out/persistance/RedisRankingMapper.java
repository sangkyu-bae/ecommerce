package org.example.ranking.adapter.out.persistance;

import lombok.RequiredArgsConstructor;
import org.example.ranking.adapter.out.persistance.entity.RedisRankingEntity;
import org.example.ranking.domain.Ranking;
import org.example.ranking.domain.RedisRanking;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisRankingMapper {

    public RedisRanking mapToRedisDomainEntityByClick(RedisRankingEntity rankingEntity){

        return RedisRanking.createGenerateRedisRanking(
                new RedisRanking.RedisRankingProductId(rankingEntity.getProductId()),
                new RedisRanking.RedisRankingProductName(rankingEntity.getProductName()),
                new RedisRanking.RedisRankingScore((long) rankingEntity.getViewCount())
        );
    }
}
