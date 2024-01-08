package org.example.ranking.tasklet;

import lombok.RequiredArgsConstructor;
import org.example.ranking.application.port.out.FindRankingRedisPort;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RankingTasklet implements Tasklet {

    private final FindRankingRedisPort findRankingRedisPort;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        return null;
    }
}
