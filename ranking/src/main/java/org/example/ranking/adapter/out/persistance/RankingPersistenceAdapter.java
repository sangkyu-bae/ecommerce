package org.example.ranking.adapter.out.persistance;

import lombok.RequiredArgsConstructor;
import org.example.PersistenceAdapter;
import org.example.ranking.adapter.out.persistance.entity.RankingEntity;
import org.example.ranking.adapter.out.persistance.repository.RankingRepository;
import org.example.ranking.application.port.out.FindRankingPort;
import org.example.ranking.application.port.out.RegisterRankingPort;
import org.example.ranking.domain.Ranking;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class RankingPersistenceAdapter implements RegisterRankingPort , FindRankingPort {
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

    @Override
    public List<RankingEntity> bulkRegisterRanking(List<Ranking> rankingList) {
        List<RankingEntity> rankingEntities = rankingList.stream()
                .map(rank -> RankingEntity.builder()
                        .productId(rank.getProductId())
                        .saleNum(rank.getSaleNum())
                        .clickNum(rank.getClickNum())
                        .build()
                ).collect(Collectors.toList());

        return rankingRepository.saveAll(rankingEntities);
    }


    @Override
    public List<RankingEntity> findRankByClickAndLimit(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<RankingEntity> result = rankingRepository.findWithPagingOrderByClickNum(pageable);
        return result;
    }
}
