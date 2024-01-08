package org.example.ranking.adapter.out.persistance;

import lombok.RequiredArgsConstructor;
import org.example.PersistenceAdapter;
import org.example.ranking.application.port.out.FindRankingRedisPort;
import org.example.ranking.application.port.out.UpdateRankingRedisPort;
import org.example.ranking.domain.Ranking;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class RedisRankingAdapter implements UpdateRankingRedisPort, FindRankingRedisPort {
    private static final String PRODUCT_KEY = "leaderProduct";

    private final StringRedisTemplate redisTemplate;
    @Override
    public void updateClickRanking(Ranking.RankingProductName rankingProductName) {
        String productName = rankingProductName.getProductName();

        int clickCnt = getProductClickCount(productName);
        incrementClickCount(productName,clickCnt);
    }

    private void incrementClickCount(String productName, int clickCnt){
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        zSetOps.add(PRODUCT_KEY,productName,clickCnt+1);
    }

    private int getProductClickCount(String productName){
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Double score = zSetOps.score(PRODUCT_KEY, productName);
        if (score != null) {
            return (int) Math.round(score);
        } else {
            return 0;
        }
    }

    @Override
    public List<RedisRanking> findRankingAll() {
        return null;
    }
}
