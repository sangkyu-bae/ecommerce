package org.example.ranking.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.ranking.application.port.in.command.FindRankingCommand;
import org.example.ranking.application.port.in.usecase.FindRankingUseCase;
import org.example.ranking.application.port.out.FindRankingRedisPort;
import org.example.ranking.domain.RedisRanking;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class FindRankingService implements FindRankingUseCase {

    private final FindRankingRedisPort findRankingRedisPort;
    @Override
    public List<RedisRanking> findRankByClickAndSize(FindRankingCommand command) {


        return null;
    }
}
