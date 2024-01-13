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

import java.util.List;
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
    public void updateClickRankingBySortedSet(Ranking.RankingProductName rankingProductName) {
        String productName = rankingProductName.getProductName();
        int clickCnt = getProductCountBySortedSet(PRODUCT_CLICK_RANK_KEY,productName);
        incrementCountBySortedSet(PRODUCT_CLICK_RANK_KEY,productName, clickCnt);
    }

    @Override
    public void updateSaleRakingBySortedSet(Ranking.RankingProductName rankingProductName) {
        String productName = rankingProductName.getProductName();
        int saleCnt = getProductCountBySortedSet(PRODUCT_SALE_RANK_KEY,productName);
        incrementCountBySortedSet(PRODUCT_SALE_RANK_KEY,productName,saleCnt);
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
                                    .productName(redisRanking.getProductName())
                                    .build();

                            hashOperations.increment(rankKey, hashKey, 1);
                            redisRankingRepository.save(rankingEntity);
                        });
    }
    @Override
    public void removeRakingAll() {
        redisRankingRepository.deleteAll();
    }
    private void incrementCountBySortedSet(String keyName, String productName, int clickCnt) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        zSetOps.add(keyName, productName, clickCnt + 1);
    }

    private int getProductCountBySortedSet(String keyName, String productName) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Double score = zSetOps.score(keyName, productName);
        if (score != null) {
            return (int) Math.round(score);
        } else {
            return 0;
        }
    }




}
