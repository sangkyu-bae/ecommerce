package org.example.ranking.application.port.in.usecase;

import org.example.ranking.application.port.in.command.UpdateRankingCommand;
import org.example.ranking.domain.RankingEvent;

public interface UpdateRankingUseCase {
    void updateClickRanking(UpdateRankingCommand command);

    void updateSaleRanking(UpdateRankingCommand command);

    void updateRanking(UpdateRankingCommand command, RankingEvent event);
}
