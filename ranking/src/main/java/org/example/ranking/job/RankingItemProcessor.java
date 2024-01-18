package org.example.ranking.job;

import org.example.ranking.adapter.out.persistance.entity.RankingEntity;
import org.example.ranking.adapter.out.persistance.entity.RedisRankingEntity;
import org.example.ranking.application.port.in.command.BulkRegisterRankingCommand;
import org.example.ranking.application.port.in.usecase.RegisterRankingUseCase;
import org.example.ranking.application.port.out.FindRankingRedisPort;
import org.example.ranking.application.port.out.RemoveRankingRedisPort;
import org.example.ranking.application.port.out.UpdateRankingRedisPort;
import org.example.ranking.domain.Ranking;
import org.example.ranking.domain.RedisRanking;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStream;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * step 처리시 redis에 저장된 ranking 데이터를 한번만 읽도록 하기 위한 커스텀 Processor
 * */
public class RankingItemProcessor implements ItemProcessor<RankingEntity, RankingEntity>, ItemStream {

    @Autowired
    private FindRankingRedisPort findRankingRedisPort;

    @Autowired
    private RemoveRankingRedisPort removeRankingRedisPort;

    @Autowired
    private UpdateRankingRedisPort updateRankingRedisPort;

    @Autowired
    private RegisterRankingUseCase registerRankingUseCase;

    private Map<Long, RedisRankingEntity> rankingMap;

    private List<Ranking> reloadRedisRankList;

    @Override
    public void open(ExecutionContext executionContext) {
        // 스텝 시작 시 호출되는 메서드
        initializeRankingMap();
    }

    @Override
    public void update(ExecutionContext executionContext) {
        // 스텝 실행 중에 주기적으로 호출되는 메서드
        // (이 메서드를 사용하여 중간에 상태를 저장할 수 있음)
    }

    @Override
    public void close() {
        // 스텝 종료 시 호출되는 메서드
        // (여기서는 특별히 해야 할 일이 없다면 비워둬도 됨)

        for(Ranking ranking : reloadRedisRankList){
            updateRankingRedisPort.reloadRankBySortedSet(ranking);
        }

        removeRankingRedisPort.removeRakingAll();
        creteRankEntity();
    }

    private void initializeRankingMap() {
        List<RedisRankingEntity> redisRankingList = findRankingRedisPort.findRankingAll();
        rankingMap = new HashMap<>();

        reloadRedisRankList = new ArrayList<>();
        for (RedisRankingEntity rankingEntity : redisRankingList) {
            rankingMap.put(rankingEntity.getProductId(), rankingEntity);
        }
    }

    private void creteRankEntity(){
        if(rankingMap.isEmpty()){
            return;
        }

        List<BulkRegisterRankingCommand> bulkRegisterRankingCommandList = rankingMap.values().stream()
                .map(rank -> BulkRegisterRankingCommand
                        .builder()
                        .productId(rank.getProductId())
                        .saleNum(rank.getSaleCount())
                        .clickNum(rank.getViewCount())
                        .build()
                ).collect(Collectors.toList());

        registerRankingUseCase.bulkRegisterRanking(bulkRegisterRankingCommandList);

    }

    @Override
    public RankingEntity process(RankingEntity rankingEntity) throws Exception {
        RedisRankingEntity redisRanking = rankingMap.get(rankingEntity.getId());

        int clickCnt = redisRanking.getViewCount();
        int saleCnt = redisRanking.getSaleCount();

        rankingEntity.changeBatchData(clickCnt,saleCnt);

        rankingMap.remove(rankingEntity.getId());

        Ranking reloadRanking = Ranking.createGenerateRanking(
                new Ranking.RankingId(rankingEntity.getProductId()),
                new Ranking.RankingProductId(rankingEntity.getProductId()),
                new Ranking.RankingClickNum(rankingEntity.getClickNum()),
                new Ranking.RankingSaleNum(rankingEntity.getSaleNum())
        );

        reloadRedisRankList.add(reloadRanking);

        return rankingEntity;
    }
}
