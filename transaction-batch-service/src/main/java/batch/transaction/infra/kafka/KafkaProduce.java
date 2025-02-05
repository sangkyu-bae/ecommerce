package batch.transaction.infra.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProduce {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    @Retryable(
            value = Exception.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 500)
    )
    public void sendMessage(String topic,Object messageObj){
        String message = "";
        try{
            message = objectMapper.writeValueAsString(messageObj);
            log.info("send msg : [{}] ",message);
            log.info("send Topic : [{}] ",topic);
            kafkaTemplate.send(topic,message).get();
        }catch (Exception e){
            log.error("sendMessage Error Topic: [{}]",topic);
            log.error("sendMessage Error message: [{}]",message);
        }
    }

    @Retryable(
            value = Exception.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 500)
    )
    public void sendMessage(String topic,String message){
        try{
            log.info("send msg : [{}] ",message);
            log.info("send Topic : [{}] ",topic);
            kafkaTemplate.send(topic,message).get();
        }catch (Exception e){
            log.error("sendMessage Error Topic: [{}]",topic);
            log.error("sendMessage Error message: [{}]",message);
        }
    }
}
