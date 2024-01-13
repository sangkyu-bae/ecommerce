package org.example.ranking.application.port.in.usecase;

import org.example.ranking.application.port.in.command.FindRankingCommand;
import org.example.ranking.domain.RedisRanking;

import java.util.List;

public interface FindRankingUseCase {

    List<RedisRanking> findRankByClickAndSize(FindRankingCommand command);
}
