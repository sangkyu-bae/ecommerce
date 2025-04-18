package batch.transaction.infra.job;

import batch.transaction.infra.batch.Base;
import batch.transaction.infra.kafka.KafkaProduce;
import batch.transaction.module.event.Event;
import batch.transaction.module.event.EventStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@Slf4j
public class TransactionOutJobConfig extends Base {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private  StepBuilderFactory stepBuilderFactory;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private KafkaProduce kafkaProduce;

    @Value("${kafka.order.event.topic}")
    private String orderEventTopic;

    @Bean
    public Job TransactionOutJob() throws Exception {

        Job transactionOutJob = jobBuilderFactory.get("transactionOutJob")
                .start(Step())
                .build();

        return transactionOutJob;
    }

    @Bean
    @JobScope
    public Step Step() throws Exception {
        return stepBuilderFactory.get("Step")
                .<Event,Event>chunk(10)
                .reader(reader(null))
                .processor(processor())
                .writer(writer())
                .build();
    }


    @Bean
    @StepScope
    public JdbcPagingItemReader<Event> reader(@Value("#{jobParameters[date]}")  String date) throws Exception {

        Map<String,Object> parameterValues = new HashMap<>();
        parameterValues.put("date", date);

        log.info("date : [{}]",date);

        StringBuilder select = new StringBuilder();
        select.append("SELECT ID, EVENT_DATA, EVENT_STATUS, create_at");

        StringBuilder from = new StringBuilder();
        from.append("FROM TB_EVENT");

        StringBuilder where = new StringBuilder();
        where.append(" WHERE create_at <= :date ");
        where.append("  AND EVENT_STATUS IN ('FAIL','INIT') ");


        return new JdbcPagingItemReaderBuilder<Event>()
                .pageSize(10)
                .fetchSize(10)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(Event.class))
                .queryProvider(customQueryProvider(dataSource, select.toString(), from.toString(),where.toString(),"create_at"))
                .parameterValues(parameterValues)
                .name("JdbcPagingItemReader")
                .build();
    }


    @Bean
    @StepScope
    public ItemProcessor<Event, Event> processor(){

        return new ItemProcessor<Event, Event>() {
            @Override
            public Event process(Event event) throws Exception {
                try{
                    log.info("event : ", event.toString() );
                    kafkaProduce.sendMessage(orderEventTopic,event.getEventData());
                    event.updateStatus(EventStatus.SUCCESS);
                }catch (Exception e){
                    log.error("delivery send fail");
                    e.printStackTrace();
                    event.updateStatus(EventStatus.FAIL);
                }
                return event;
            }
        };
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<Event> writer(){

        return new JdbcBatchItemWriterBuilder<Event>()
                .dataSource(dataSource)
                .sql("UPDATE TB_EVENT SET EVENT_STATUS = :status WHERE ID = :id")
                .beanMapped()
                .build();

    }

}
