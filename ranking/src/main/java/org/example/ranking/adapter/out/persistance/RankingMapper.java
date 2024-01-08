package org.example.ranking.adapter.out.persistance;

import lombok.RequiredArgsConstructor;
import org.example.ranking.adapter.out.persistance.entity.RankingEntity;
import org.example.ranking.domain.Ranking;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RankingMapper {

    public Ranking mapToDomainEntity(RankingEntity rankingEntity){
        return Ranking.createGenerateRanking(
                new Ranking.RankingId(rankingEntity.getId()),
                new Ranking.RankingProductId(rankingEntity.getProductId()),
                new Ranking.RankingProductName(rankingEntity.getProductName()),
                new Ranking.RankingClickNum(rankingEntity.getClickNum()),
                new Ranking.RankingSaleNum(rankingEntity.getSaleNum())
        );
    }
}
