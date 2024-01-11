package org.example.ranking.adapter.out.persistance;

import lombok.RequiredArgsConstructor;
import org.example.PersistenceAdapter;
import org.example.ranking.adapter.out.persistance.entity.RedisRankingEntity;
import org.example.ranking.adapter.out.persistance.repository.RedisRankingRepository;
import org.example.ranking.application.port.out.FindRankingRedisPort;
import org.example.ranking.application.port.out.RemoveRankingRedisPort;
import org.example.ranking.application.port.out.UpdateRankingRedisPort;
import org.example.ranking.domain.Ranking;
import org.example.ranking.domain.RedisRanking;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@PersistenceAdapter
@RequiredArgsConstructor
public class RedisRankingAdapter implements UpdateRankingRedisPort, FindRankingRedisPort, RemoveRankingRedisPort {
    private static final String PRODUCT_KEY = "leaderProduct";

    private static final String PRODUCT_RANK_KEY = "productView";

    private final StringRedisTemplate redisTemplate;
    private final RedisRankingRepository redisRankingRepository;

    @Override
    public List<RedisRankingEntity> findRankingAll() {
        Iterable<RedisRankingEntity> iterable = redisRankingRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void updateClickRankingBySortedSet(Ranking.RankingProductName rankingProductName) {
        String productName = rankingProductName.getProductName();
        int clickCnt = getProductClickCountBySortedSet(productName);
        incrementClickCountBySortedSet(productName, clickCnt);
    }

    @Override
    public void updateClickRankingView(RedisRanking redisRanking) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        String productViewKey = PRODUCT_RANK_KEY + ":" + redisRanking.getProductId();

        redisRankingRepository.findById(productViewKey)
                .ifPresentOrElse(
                        findRanking -> {
                            hashOperations.increment(productViewKey, "viewCount", 1);
                        },
                        () -> {
                            RedisRankingEntity rankingEntity = RedisRankingEntity.builder()
                                    .id(productViewKey)
                                    .productId(redisRanking.getProductId())
                                    .productName(redisRanking.getProductName())
                                    .build();

                            hashOperations.increment(productViewKey, "viewCount", 1);
                            redisRankingRepository.save(rankingEntity);
                        });
    }

    @Override
    public void updateSaleRankingView(RedisRanking redisRanking) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        String productViewKey = PRODUCT_RANK_KEY + ":" + redisRanking.getProductId();

        redisRankingRepository.findById(productViewKey)
                .ifPresentOrElse(
                        findRanking -> {
                            hashOperations.increment(productViewKey, "saleCount", 1);
                        },
                        () -> {
                            RedisRankingEntity rankingEntity = RedisRankingEntity.builder()
                                    .id(productViewKey)
                                    .productId(redisRanking.getProductId())
                                    .productName(redisRanking.getProductName())
                                    .build();

                            hashOperations.increment(productViewKey, "saleCount", 1);
                            redisRankingRepository.save(rankingEntity);
                        });
    }
    @Override
    public void removeRakingAll() {
        redisRankingRepository.deleteAll();
    }
    private void incrementClickCountBySortedSet(String productName, int clickCnt) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        zSetOps.add(PRODUCT_KEY, productName, clickCnt + 1);
    }

    private int getProductClickCountBySortedSet(String productName) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Double score = zSetOps.score(PRODUCT_KEY, productName);
        if (score != null) {
            return (int) Math.round(score);
        } else {
            return 0;
        }
    }


}
