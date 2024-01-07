package org.example.ranking.application.port.in.usecase;

import org.example.ranking.application.port.in.command.RegisterRankingCommand;
import org.example.ranking.domain.Ranking;

public interface RegisterRankingUseCase {
    Ranking registerRaking(RegisterRankingCommand command);
}
