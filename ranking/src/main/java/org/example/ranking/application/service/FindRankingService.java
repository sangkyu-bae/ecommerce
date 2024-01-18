package org.example.ranking.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.ranking.adapter.out.persistance.RedisRankingMapper;
import org.example.ranking.adapter.out.persistance.entity.RedisRankingEntity;
import org.example.ranking.adapter.out.persistance.repository.RedisRankingRepository;
import org.example.ranking.application.port.in.command.FindRankingCommand;
import org.example.ranking.application.port.in.usecase.FindRankingUseCase;
import org.example.ranking.application.port.out.FindRankingRedisPort;
import org.example.ranking.domain.RedisRanking;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class FindRankingService implements FindRankingUseCase {
    private final FindRankingRedisPort findRankingRedisPort;

    private final RedisRankingMapper redisRankingMapper;

    @Value("${redis_key.product.rank}")
    private String PRODUCT_RANK_KEY;
    @Override
    public List<RedisRanking> findRankByClickAndLimit(FindRankingCommand command) {
        List<String> clickRank = findRankingRedisPort.findRankingByClickAndLimit(command.getLimit());
        List<RedisRanking> redisRankingList = getRedisRankings(clickRank);

        return redisRankingList;
    }

    @Override
    public List<RedisRanking> findRankBySaleAndLimit(FindRankingCommand command) {
        List<String> clickRank = findRankingRedisPort.findRankingBySaleAndLimit(command.getLimit());
        List<RedisRanking> redisRankingList = getRedisRankings(clickRank);

        return redisRankingList;
    }

    private List<RedisRanking> getRedisRankings(List<String> ranks) {
        List<String> redisRankIdKeys = ranks.stream()
                .map(rank->PRODUCT_RANK_KEY + ":" + rank).collect(Collectors.toList());

        List<RedisRankingEntity> redisRankingList = findRankingRedisPort.findRankInId(redisRankIdKeys);

        return redisRankingList.stream()
                .map(rank -> redisRankingMapper.mapToRedisDomainEntityByClick(rank))
                .collect(Collectors.toList());
    }
}
