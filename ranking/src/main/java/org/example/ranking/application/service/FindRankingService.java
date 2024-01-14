package org.example.ranking.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.ranking.application.port.in.command.FindRankingCommand;
import org.example.ranking.application.port.in.usecase.FindRankingUseCase;
import org.example.ranking.application.port.out.FindRankingRedisPort;
import org.example.ranking.domain.RedisRanking;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UseCase
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class FindRankingService implements FindRankingUseCase {

    private final FindRankingRedisPort findRankingRedisPort;
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
        List<RedisRanking> redisRankingList = new ArrayList<>();

        for(int i = 0; i < ranks.size() ; i++){
            redisRankingList.add(
                    RedisRanking.createGenerateRedisRanking(
                            new RedisRanking.RedisRankingProductId(Long.valueOf(ranks.get(i))),
                            new RedisRanking.RedisRankingScore((long) (i+1))
                    )
            );
        }
        return redisRankingList;
    }
}
