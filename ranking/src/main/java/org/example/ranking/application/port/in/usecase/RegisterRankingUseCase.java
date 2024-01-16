package org.example.ranking.application.port.in.usecase;

import org.example.ranking.application.port.in.command.BulkRegisterRankingCommand;
import org.example.ranking.application.port.in.command.RegisterRankingCommand;
import org.example.ranking.domain.Ranking;

import java.util.List;

public interface RegisterRankingUseCase {
    Ranking registerRaking(RegisterRankingCommand command);
    List<Ranking> bulkRegisterRanking(List<BulkRegisterRankingCommand> commands);
}
