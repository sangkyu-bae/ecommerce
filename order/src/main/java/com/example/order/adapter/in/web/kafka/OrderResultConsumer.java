package com.example.order.adapter.in.web.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.CountDownLatchManager;
import org.example.task.MemberTask;
import org.example.task.OrderSubTask;
import org.example.task.OrderTask;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderResultConsumer {

    private final ObjectMapper objectMapper;
    private final CountDownLatchManager countDownLatchManager;

    @KafkaListener(topics ="${kafka.order.result.topic}",groupId = "${kafka.task.group}")
    public void resultTaskListener(String resultTaskMessage){
        OrderTask task = null;
        try{
            task = objectMapper.readValue(resultTaskMessage, OrderTask.class);
            String result = "success";
            for(OrderSubTask subTask :task.getSubTaskList()){
                if(subTask.getStatus() == OrderSubTask.Status.FAIL){
                    result = "fail";
                    break;
                }
            }
            this.countDownLatchManager.setDataForKey(task.getTaskId(),result);
            this.countDownLatchManager.getCountDownLatch(task.getTaskId()).countDown();
        }catch (Exception e){
            log.error("resultTaskListener Error message = {}",resultTaskMessage,e);
        }
    }
}
