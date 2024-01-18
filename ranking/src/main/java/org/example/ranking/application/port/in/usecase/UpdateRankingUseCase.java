package org.example.ranking.application.port.in.usecase;

import org.example.ranking.application.port.in.command.UpdateRankingCommand;
import org.example.ranking.domain.RankingEvent;

public interface UpdateRankingUseCase {
    void updateRanking(UpdateRankingCommand command, RankingEvent event);
}
