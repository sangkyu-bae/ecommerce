package org.example.ranking.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.WebAdapter;
import org.example.ranking.application.port.in.command.FindRankingCommand;
import org.example.ranking.application.port.in.command.UpdateRankingCommand;
import org.example.ranking.application.port.in.usecase.FindRankingUseCase;
import org.example.ranking.application.port.in.usecase.UpdateRankingUseCase;
import org.example.ranking.domain.Ranking;
import org.example.ranking.domain.RankingEvent;
import org.example.ranking.domain.RedisRanking;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebAdapter
@RequiredArgsConstructor
@RestController
@Slf4j
public class FindRankController {

    private final FindRankingUseCase findRankingUseCase;

    private final RedisTemplate redisTemplate;
    @GetMapping("/rank/click/{limit}")
    public ResponseEntity<List<RedisRanking>> findRankByClick(@PathVariable("limit") int limit){

        FindRankingCommand command = FindRankingCommand.builder()
                .limit(limit)
                .build();

        List<RedisRanking> redisRankingList = findRankingUseCase.findRankByClickAndLimit(command);

        return ResponseEntity.ok().body(redisRankingList);
//        long startTime = System.currentTimeMillis();
//
//        ZSetOperations zSetOps  = redisTemplate.opsForZSet();
//        Set<String> rangeRakingSet = zSetOps.reverseRange("CLICK_RANK",0,limit-1);
//
//
//        long endTime = System.currentTimeMillis();
//        long findTime = endTime - startTime;
//        log.info("findTime : {}", findTime);
//
//        List<String> d= new ArrayList<>(rangeRakingSet);
//
//        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/rank/click/db/{limit}")
    public ResponseEntity<List<Ranking>> findRankDbByClick(@PathVariable("limit") int limit){
        long startTime = System.currentTimeMillis();
        FindRankingCommand command = FindRankingCommand.builder()
                .limit(limit)
                .build();

        List<Ranking> rankingList = findRankingUseCase.findClickDbClickAndLimit(command);
        long endTime = System.currentTimeMillis();
        long findTime = endTime - startTime;
        log.info("findTime : {}", findTime);
        return ResponseEntity.ok().body(rankingList);
    }

    @GetMapping("/rank/sale/{limit}")
    public ResponseEntity<List<RedisRanking>> findRankBySale(@PathVariable("limit") int limit){

        FindRankingCommand command = FindRankingCommand.builder()
                .limit(limit)
                .build();

        List<RedisRanking> redisRankingList = findRankingUseCase.findRankBySaleAndLimit(command);

        return ResponseEntity.ok().body(redisRankingList);
    }
}
