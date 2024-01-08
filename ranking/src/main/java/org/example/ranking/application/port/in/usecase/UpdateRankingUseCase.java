package org.example.ranking.application.port.in.usecase;

import org.example.ranking.application.port.in.command.UpdateClickRankingCommand;
import org.example.ranking.domain.Ranking;

public interface UpdateRankingUseCase {

    void updateClickRanking(UpdateClickRankingCommand command);
}
