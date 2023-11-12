package com.example.taskconsumer.consum;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.CountDownLatchManager;
import org.example.task.MemberTask;
import org.example.task.OrderSubTask;
import org.example.task.ProductTask;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderSubTaskConsumer {
    private final ObjectMapper objectMapper;

    private final CountDownLatchManager countDownLatchManager;
    @KafkaListener(topics ="${kafka.topic}",groupId = "${kafka.task.group}")
    public void memberTaskListener(String memberTaskMessage){
        MemberTask task = null;
        try{
            task = objectMapper.readValue(memberTaskMessage, MemberTask.class);
            setCountManager(task,"createMemberTask");
        }catch (Exception e){
            log.error("taskListener Error message = {}",memberTaskMessage,e);
        }
    }

    @KafkaListener(topics ="${kafka.topic}",groupId = "${kafka.task.group}")
    public void ProductTaskListener(String memberTaskMessage){
        ProductTask task = null;
        try{
            task = objectMapper.readValue(memberTaskMessage, ProductTask.class);
            setCountManager(task,"createProductTask");
        }catch (Exception e){
            log.error("taskListener Error message = {}",memberTaskMessage,e);
        }
    }

    private void setCountManager(OrderSubTask task, String latchKey) {
        boolean taskResult = true;

        if(task.getStatus()!= OrderSubTask.Status.SUCCESS){
            taskResult = false;
        }
        if (taskResult) {
            this.countDownLatchManager.setDataForKey(task.getTaskId(), "success");
        } else{
            this.countDownLatchManager.setDataForKey(task.getTaskId(), "fail");
        }
        this.countDownLatchManager.getCountDownLatch(latchKey).countDown();
    }
}
