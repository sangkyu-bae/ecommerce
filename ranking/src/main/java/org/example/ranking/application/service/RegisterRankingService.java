package org.example.ranking.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;
import org.example.ranking.adapter.out.persistance.RankingMapper;
import org.example.ranking.adapter.out.persistance.entity.RankingEntity;
import org.example.ranking.adapter.out.persistance.entity.RedisRankingEntity;
import org.example.ranking.application.port.in.command.BulkRegisterRankingCommand;
import org.example.ranking.application.port.in.command.RegisterRankingCommand;
import org.example.ranking.application.port.in.usecase.RegisterRankingUseCase;
import org.example.ranking.application.port.out.RegisterRankingPort;
import org.example.ranking.domain.Ranking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@UseCase
public class RegisterRankingService implements RegisterRankingUseCase {

    private final RegisterRankingPort registerRankingPort;

    private final RankingMapper rankingMapper;

    @Override
    public Ranking registerRaking(RegisterRankingCommand command) {
        Ranking ranking = Ranking.createGenerateRanking(
                new Ranking.RankingId(null),
                new Ranking.RankingProductId(command.getProductId()),
                new Ranking.RankingClickNum(0),
                new Ranking.RankingSaleNum(0)
        );

        RankingEntity registerEntity = registerRankingPort.registerRanking(ranking);

        return rankingMapper.mapToDomainEntity(registerEntity);
    }

    @Override
    public List<Ranking> bulkRegisterRanking(List<BulkRegisterRankingCommand> commands) {
        List<Ranking> rankingEntities = new ArrayList<>();
        for (BulkRegisterRankingCommand command  : commands){
            Ranking ranking = Ranking.createGenerateRanking(
                    new Ranking.RankingId(null),
                    new Ranking.RankingProductId(command.getProductId()),
                    new Ranking.RankingClickNum(command.getClickNum()),
                    new Ranking.RankingSaleNum(command.getSaleNum())
            );
            rankingEntities.add(ranking);
        }
        List<RankingEntity> bulkRankingList = registerRankingPort.bulkRegisterRanking(rankingEntities);

        return bulkRankingList.stream()
                .map(rank -> rankingMapper.mapToDomainEntity(rank))
                .collect(Collectors.toList());
    }


}
