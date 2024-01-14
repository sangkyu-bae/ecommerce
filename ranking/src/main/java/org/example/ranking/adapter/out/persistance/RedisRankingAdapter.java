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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@PersistenceAdapter
@RequiredArgsConstructor
public class RedisRankingAdapter implements UpdateRankingRedisPort, FindRankingRedisPort, RemoveRankingRedisPort {
    @Value("${product.click.key}")
    private static String PRODUCT_CLICK_RANK_KEY;

    @Value("${product.sale.key}")
    private static String PRODUCT_SALE_RANK_KEY;

    @Value("${product.rank.key}")
    private static String PRODUCT_RANK_KEY;

    private final StringRedisTemplate redisTemplate;
    private final RedisRankingRepository redisRankingRepository;

    @Override
    public List<RedisRankingEntity> findRankingAll() {
        Iterable<RedisRankingEntity> iterable = redisRankingRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findRankingByClickAndLimit(int limit) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Set<String> rangeRakingSet = zSetOps.reverseRange(PRODUCT_CLICK_RANK_KEY,0,limit-1);
        return new ArrayList<>(rangeRakingSet);
    }

    @Override
    public List<String> findRankingBySaleAndLimit(int limit) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Set<String> rangeRakingSet = zSetOps.reverseRange(PRODUCT_SALE_RANK_KEY,0,limit-1);
        return new ArrayList<>(rangeRakingSet);
    }

    @Override
    public void updateClickRankingBySortedSet(Ranking.RankingProductId rankingProductId) {
        long productId = rankingProductId.getProductId();
        int clickCnt = getProductCountBySortedSet(PRODUCT_CLICK_RANK_KEY,productId);
        incrementCountBySortedSet(PRODUCT_CLICK_RANK_KEY,productId, clickCnt);
    }

    @Override
    public void updateSaleRakingBySortedSet(Ranking.RankingProductId rankingProductId) {
        long productId = rankingProductId.getProductId();
        int saleCnt = getProductCountBySortedSet(PRODUCT_SALE_RANK_KEY,productId);
        incrementCountBySortedSet(PRODUCT_SALE_RANK_KEY,productId,saleCnt);
    }

    @Override
    public void updateClickRankingView(RedisRanking redisRanking) {
        String productViewKey = PRODUCT_RANK_KEY + ":" + redisRanking.getProductId();
        updateRanking(productViewKey,"viewCount",redisRanking);
    }

    @Override
    public void updateSaleRankingView(RedisRanking redisRanking) {
        String productViewKey = PRODUCT_RANK_KEY + ":" + redisRanking.getProductId();
        updateRanking(productViewKey,"saleCount",redisRanking);
    }

    private void updateRanking(String rankKey,String hashKey,RedisRanking redisRanking){
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();

        redisRankingRepository.findById(rankKey)
                .ifPresentOrElse(
                        findRanking -> {
                            hashOperations.increment(rankKey, hashKey, 1);
                        },
                        () -> {
                            RedisRankingEntity rankingEntity = RedisRankingEntity.builder()
                                    .id(rankKey)
                                    .productId(redisRanking.getProductId())
                                    .build();

                            hashOperations.increment(rankKey, hashKey, 1);
                            redisRankingRepository.save(rankingEntity);
                        });
    }
    @Override
    public void removeRakingAll() {
        redisRankingRepository.deleteAll();
    }
    private void incrementCountBySortedSet(String keyName, long productId, int clickCnt) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        zSetOps.add(keyName, productId, clickCnt + 1);
    }

    private int getProductCountBySortedSet(String keyName, long productId) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Double score = zSetOps.score(keyName, productId);
        if (score != null) {
            return (int) Math.round(score);
        } else {
            return 0;
        }
    }


}
