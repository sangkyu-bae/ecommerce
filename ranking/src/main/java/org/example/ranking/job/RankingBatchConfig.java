package org.example.ranking.job;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ranking.adapter.out.persistance.entity.RankingEntity;
import org.example.ranking.adapter.out.persistance.repository.RankingRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.batch.job.names", havingValue = "BatchConfig")
public class RankingBatchConfig {
    private final RankingRepository rankingRepository;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;



    @Bean
    public Job rakingUpdateJob()throws Exception{
        return jobBuilderFactory.get("updateRankingJob")
                .start(rankingUpdateStep()).build();
    }
    @Bean
    @JobScope
    public Step rankingUpdateStep()throws Exception{
        return stepBuilderFactory.get("updateRankingStep")
                .<RankingEntity, RankingEntity>chunk(100)
                .reader(rankingReader())
                .writer(myWriter())
                .build();
    }
    @Bean
    @StepScope
    public JpaPagingItemReader<RankingEntity> rankingReader(){
        JpaPagingItemReader<RankingEntity> rankingEntityJpaPagingItemReader = new JpaPagingItemReader<>();

        rankingEntityJpaPagingItemReader.setQueryString("select r from RankingEntity r");
        rankingEntityJpaPagingItemReader.setEntityManagerFactory(entityManagerFactory);
        rankingEntityJpaPagingItemReader.setPageSize(100);

        return rankingEntityJpaPagingItemReader;
    }

    private ItemProcessor<RankingEntity,RankingEntity> rankingProcessor(){
        return null;
    }
    @Bean
    @StepScope
    public ItemWriter<RankingEntity> myWriter() {
        return ((List<? extends RankingEntity> rankingEntities) ->
                rankingRepository.saveAll(rankingEntities));
    }
}
