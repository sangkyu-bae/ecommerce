package org.example.ranking.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.example.ranking.adapter.out.persistance.entity.RankingEntity;
import org.example.ranking.adapter.out.persistance.entity.RedisRankingEntity;
import org.example.ranking.adapter.out.persistance.repository.RankingRepository;
import org.example.ranking.adapter.out.persistance.repository.RedisRankingRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@Slf4j
public class RegisterRankController {

    private final RankingRepository rankingRepository;

    private final StringRedisTemplate redisTemplate;

    private final RedisRankingRepository redisRankingRepository;


    @PostMapping("/rank")
    public ResponseEntity<String> test(){

        List<RankingEntity> rankingEntities = new ArrayList<>();

        for(int i = 0 ;i < 20000 ;i++){
            RankingEntity rankingEntity = RankingEntity.builder()
                    .productId(i+1)
                    .saleNum((i+1)*150)
                    .clickNum((i+1)*200)
                    .createAt(LocalDateTime.now())
                    .updateAt(LocalDateTime.now())
                    .productName("test Product")
                    .build();
            rankingEntities.add(rankingEntity);
        }

        rankingRepository.saveAll(rankingEntities);

        return ResponseEntity.ok().body("success");
    }

    @PostMapping("/rank/redis")
    public ResponseEntity<String> redisTest(){

        List<RankingEntity> rankingEntities= rankingRepository.findAll();
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        int i = 0;
        for(RankingEntity rankingEntity : rankingEntities){

            String productViewKey = "RANK:" + rankingEntity.getProductId();
            RedisRankingEntity rankingRedisEntity = RedisRankingEntity.builder()
                    .id(productViewKey)
                    .productId(rankingEntity.getProductId())
                    .productName(rankingEntity.getProductName())
                    .build();

            hashOperations.increment(productViewKey, "viewCount", rankingEntity.getClickNum());
            redisRankingRepository.save(rankingRedisEntity);
            ZSetOperations zSetOps = redisTemplate.opsForZSet();
            String productKey =String.valueOf(rankingEntity.getProductId());
            zSetOps.add("CLICK_RANK", productKey, rankingEntity.getClickNum());
            i++;
            if(i ==100){
                break;
            }
        }

        return ResponseEntity.ok().body("success");
    }
}
