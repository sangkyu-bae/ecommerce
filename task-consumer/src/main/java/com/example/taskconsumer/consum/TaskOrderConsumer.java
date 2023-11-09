package com.example.taskconsumer.consum;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.task.OrderTask;
import org.example.task.OrderSubTask;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TaskOrderConsumer {

    private final ObjectMapper objectMapper;


    @KafkaListener(topics ="${kafka.topic}",groupId = "${kafka.task.group}")
    public void taskListener(String orderTaskMessage){
        OrderTask task = null;
        try{
             task = objectMapper.readValue(orderTaskMessage, OrderTask.class);
             for(OrderSubTask subTask : task.getSubTaskList()){
                 OrderSubTask.TaskType taskType = subTask.getTask();

                if(taskType == OrderSubTask.TaskType.MEMEBER){

                }else if(taskType == OrderSubTask.TaskType.DELIVERY){

                }
             }
        }catch (Exception e){
            log.error("taskListener Error message = {}",orderTaskMessage,e);
        }
    }

}
