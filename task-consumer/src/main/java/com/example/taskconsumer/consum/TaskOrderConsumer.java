package com.example.taskconsumer.consum;

import com.example.taskconsumer.config.AppProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.CountDownLatchManager;
import org.example.task.MemberTask;
import org.example.task.OrderTask;
import org.example.task.OrderSubTask;
import org.example.task.ProductTask;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class TaskOrderConsumer {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final AppProperties appProperties;

    private final CountDownLatchManager countDownLatchManager;

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.task.group}")
    public void taskListener(String orderTaskMessage) {
        OrderTask task = null;
        try {
            task = objectMapper.readValue(orderTaskMessage, OrderTask.class);
            task.setTaskId(UUID.randomUUID().toString());

            for (OrderSubTask subTask : task.getSubTaskList()) {
                OrderSubTask.TaskType taskType = subTask.getTaskType();
                if (taskType == OrderSubTask.TaskType.MEMBER) {
                    sendMember(subTask);
                } else if (taskType == OrderSubTask.TaskType.PRODUCT) {
                    sendProduct(subTask);
                }

                if (subTask.getStatus() == OrderSubTask.Status.FAIL) {
                    break;
                }
            }

            String inputTask = objectMapper.writeValueAsString(task);
            kafkaTemplate.send(appProperties.getOrderResultTopic(), inputTask);
        } catch (Exception e) {
            log.error("taskListener Error message = {}", orderTaskMessage, e);
        }
    }

    private void sendMember(OrderSubTask subTask) throws JsonProcessingException, InterruptedException {
        MemberTask memberTask = (MemberTask) subTask;
        String inputMemberTask = objectMapper.writeValueAsString(memberTask);
        kafkaTemplate.send(appProperties.getMemberTopic(), inputMemberTask);

        createThread("createMemberTask", subTask);
    }

    private void sendProduct(OrderSubTask subTask) throws JsonProcessingException, InterruptedException {
        ProductTask productTask = (ProductTask) subTask;
        String inputProductTask = objectMapper.writeValueAsString(productTask);
        kafkaTemplate.send(appProperties.getProductTopic(), inputProductTask);

        createThread("createProductTask", subTask);
    }

    private void createThread(String latchKey, OrderSubTask subTask) throws InterruptedException {
        countDownLatchManager.getCountDownLatch(latchKey).await();
        String result = countDownLatchManager.getDataForKey(subTask.getTaskId());

        OrderSubTask.Status taskType;
        if (result == "success") {
            taskType = OrderSubTask.Status.SUCCESS;
        } else {
            taskType = OrderSubTask.Status.FAIL;
        }
        subTask.setStatus(taskType);

        countDownLatchManager.removeDataForKey(subTask.getTaskId());
    }

//    private String setThreadData(String taskId, String result) {
//        if(!result.equals("success")){
//            countDownLatchManager.setDataForKey(taskId,"success");
//        }else{
//            this.countDownLatchManager.setDataForKey(taskId, "failed");
//        }
//
//        return result.equals("success") ? "success" : "fail";
//    }
}
