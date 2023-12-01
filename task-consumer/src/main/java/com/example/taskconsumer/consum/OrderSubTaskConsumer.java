package com.example.taskconsumer.consum;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.CountDownLatchManager;
import org.example.UseCase;
import org.example.task.MemberTask;
import org.example.task.OrderSubTask;
import org.example.task.ProductTask;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class OrderSubTaskConsumer {


    private final ObjectMapper objectMapper;

    private final CountDownLatchManager countDownLatchManager;


    @KafkaListener(topics ="${kafka.order.task.sub.member.topic}",groupId = "${kafka.order.task.sub.member.group}")
    public void memberTaskListener(String memberTaskMessage){
        MemberTask task = null;
        try{
            task = objectMapper.readValue(memberTaskMessage, MemberTask.class);
            setCountManager(task,task.getTaskId());
        }catch (Exception e){
            log.error("taskListener Error message = {}",memberTaskMessage,e);
        }
    }

    @KafkaListener(topics ="${kafka.order.task.sub.product.topic}",groupId = "${kafka.order.task.sub.product.group}")
    public void ProductTaskListener(String productTaskMessage){
        ProductTask task = null;
        try{
            task = objectMapper.readValue(productTaskMessage, ProductTask.class);
            setCountManager(task,task.getTaskId());
        }catch (Exception e){
            log.error("ProductTaskListener Error message = {}",productTaskMessage,e);
        }
    }

    private void setCountManager(OrderSubTask task, String latchKey) {
        boolean taskResult = true;
        System.out.println(task);
        if(task.getStatus() != OrderSubTask.Status.SUCCESS){
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
