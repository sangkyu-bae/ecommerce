package org.example.ranking.application.port.in.usecase;

import org.example.ranking.application.port.in.command.FindRankingCommand;
import org.example.ranking.domain.Ranking;
import org.example.ranking.domain.RedisRanking;

import java.util.List;

public interface FindRankingUseCase {

    List<RedisRanking> findRankByClickAndLimit(FindRankingCommand command);

    List<RedisRanking> findRankBySaleAndLimit(FindRankingCommand command);

    List<Ranking> findClickDbClickAndLimit(FindRankingCommand command);

}
