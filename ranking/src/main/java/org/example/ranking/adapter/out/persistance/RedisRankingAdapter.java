package org.example.ranking.adapter.out.persistance;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.example.PersistenceAdapter;
import org.example.ranking.adapter.out.persistance.entity.RedisRankingEntity;
import org.example.ranking.adapter.out.persistance.repository.RankingRepository;
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
@Slf4j
public class RedisRankingAdapter implements UpdateRankingRedisPort, FindRankingRedisPort, RemoveRankingRedisPort {
    private final RankingRepository rankingRepository;
    @Value("${redis_key.product.click}")
    private String PRODUCT_CLICK_RANK_KEY;

    @Value("${redis_key.product.sale}")
    private String PRODUCT_SALE_RANK_KEY;

    @Value("${redis_key.product.rank}")
    private String PRODUCT_RANK_KEY;

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
    public List<RedisRankingEntity> findRankInId(List<String> ids) {
        return redisRankingRepository.findByIdIn(ids);
    }

    @Override
    public void updateClickRankingBySortedSet(Ranking.RankingProductId rankingProductId) {
        long productId = rankingProductId.getProductId();
        log.info("leaderboard click key : {}", PRODUCT_CLICK_RANK_KEY);
        int clickCnt = getProductCountBySortedSet(PRODUCT_CLICK_RANK_KEY,productId);

        incrementCountBySortedSet(PRODUCT_CLICK_RANK_KEY,productId, clickCnt + 1);
    }

    @Override
    public void updateSaleRakingBySortedSet(Ranking.RankingProductId rankingProductId) {
        long productId = rankingProductId.getProductId();
        int saleCnt = getProductCountBySortedSet(PRODUCT_SALE_RANK_KEY,productId);
        incrementCountBySortedSet(PRODUCT_SALE_RANK_KEY,productId,saleCnt + 1);
    }

    @Override
    public void updateClickRankingView(RedisRanking redisRanking) {
        log.info(PRODUCT_RANK_KEY);
        String productViewKey = PRODUCT_RANK_KEY + ":" + redisRanking.getProductId();
        log.info("productVieKey : {}" ,productViewKey);
        updateRanking(productViewKey,"viewCount",redisRanking);
    }

    @Override
    public void updateSaleRankingView(RedisRanking redisRanking) {
        String productViewKey = PRODUCT_RANK_KEY + ":" + redisRanking.getProductId();
        updateRanking(productViewKey,"saleCount",redisRanking);
    }

    @Override
    public void reloadRankBySortedSet(Ranking reloadRanking) {
        long productId = reloadRanking.getProductId();

        incrementCountBySortedSet(PRODUCT_SALE_RANK_KEY,productId, (int) reloadRanking.getSaleNum());
        incrementCountBySortedSet(PRODUCT_CLICK_RANK_KEY,productId, (int) reloadRanking.getSaleNum());
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

    private void incrementCountBySortedSet(String keyName, long productId, int clickCnt) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        String productKey =String.valueOf(productId);
        zSetOps.add(keyName, productKey, clickCnt );
    }
    private int getProductCountBySortedSet(String keyName, long productId) {
        log.info("keyName : {}",keyName);
        log.info("productId : {}",productId);

        String productKey =String.valueOf(productId);
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Double score = zSetOps.score(keyName, productKey);
        log.info("score : {}",score);
        if (score != null) {
            return (int) Math.round(score);
        } else {
            return 0;
        }
    }
}
