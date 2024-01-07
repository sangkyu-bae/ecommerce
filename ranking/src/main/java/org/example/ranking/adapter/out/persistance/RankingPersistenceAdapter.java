package org.example.ranking.adapter.out.persistance;

import lombok.RequiredArgsConstructor;
import org.example.PersistenceAdapter;
import org.example.ranking.adapter.out.persistance.entity.RankingEntity;
import org.example.ranking.adapter.out.persistance.repository.RankingRepository;
import org.example.ranking.application.port.out.RegisterRankingPort;
import org.example.ranking.domain.Ranking;

@PersistenceAdapter
@RequiredArgsConstructor
public class RankingPersistenceAdapter implements RegisterRankingPort {
    private final RankingRepository rankingRepository;
    @Override
    public RankingEntity registerRanking(Ranking ranking) {

        RankingEntity rankingEntity = RankingEntity.builder()
                .productId(ranking.getProductId())
                .saleNum(ranking.getSaleNum())
                .clickNum(ranking.getClickNum())
                .build();

        return rankingRepository.save(rankingEntity);
    }
}
