//package com.example.batch.adatptor;
//
//import com.example.batch.reader.RedisEventCouponReader;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Slf4j
//@RequiredArgsConstructor
//@Configuration
////@EnableBatchProcessing
//public class CouponJobConfiguration {
//
//    private final JobBuilderFactory jobBuilderFactory; // 생성자 DI 받음
//    private final StepBuilderFactory stepBuilderFactory; // 생성자 DI 받음
//
//
//    @Bean
//    public Job job(JobBuilderFactory jobBuilderFactory, Step step) {
//        return jobBuilderFactory.get("redisSortedSetBatchJob")
//                .incrementer(new RunIdIncrementer())
//                .flow(step)
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step step(StepBuilderFactory stepBuilderFactory, RedisEventCouponReader redisEventCouponReader,
//                     ItemProcessor<String, String> itemProcessor, ItemWriter<String> itemWriter) {
//        return stepBuilderFactory.get("redisSortedSetBatchStep")
//                .<String, String>chunk(10)
//                .reader(redisEventCouponReader)
//                .processor(itemProcessor)
//                .writer(itemWriter)
//                .build();
//    }
//
//}
