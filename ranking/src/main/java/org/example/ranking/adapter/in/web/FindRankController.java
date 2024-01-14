package org.example.ranking.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.example.WebAdapter;
import org.example.ranking.application.port.in.command.FindRankingCommand;
import org.example.ranking.application.port.in.usecase.FindRankingUseCase;
import org.example.ranking.domain.RedisRanking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RequiredArgsConstructor
@RestController
public class FindRankController {

    private final FindRankingUseCase findRankingUseCase;

    @GetMapping("/rank/click/{limit}")
    public ResponseEntity<List<RedisRanking>> findRankByClick(@PathVariable("limit") int limit){

        FindRankingCommand command = FindRankingCommand.builder()
                .limit(limit)
                .build();

        findRankingUseCase.findRankByClickAndLimit(command);

        return null;
    }

}
