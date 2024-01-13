package org.example.ranking.application.port.in.usecase;

import org.example.ranking.application.port.in.command.UpdateRankingCommand;

public interface UpdateRankingUseCase {
    void updateClickRanking(UpdateRankingCommand command);

    void updateSaleRanking(UpdateRankingCommand command);
}
