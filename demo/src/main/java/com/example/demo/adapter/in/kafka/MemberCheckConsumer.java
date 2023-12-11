package com.example.demo.adapter.in.kafka;

import com.example.demo.application.port.in.command.FindMemberCommand;
import com.example.demo.application.port.in.usecase.FindMemberUseCase;
import com.example.demo.infra.config.AppProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.task.MemberTask;
import org.example.task.OrderSubTask;
import org.example.task.order.RemoveOrderTask;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberCheckConsumer {
    private final ObjectMapper objectMapper;

    private final FindMemberUseCase findMemberUseCase;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final AppProperties appProperties;

    private final Map<Boolean, OrderSubTask.Status> STATUS_MAP = Map.of(
            false, OrderSubTask.Status.FAIL,
            true, OrderSubTask.Status.SUCCESS
    );
    

    @KafkaListener(topics ="${kafka.member.task.topic}",groupId = "${kafka.member.task.group}")
    public void resultTaskListener(String memberTaskMessage){
        MemberTask task = null;
        System.out.println(memberTaskMessage);
        try{
            task = objectMapper.readValue(memberTaskMessage, MemberTask.class);
            System.out.println("task user Id? :" + task.getUserId());
            FindMemberCommand command = FindMemberCommand.builder()
                    .userId(task.getUserId())
                    .build();
            boolean isExistMember = findMemberUseCase.existMember(command);
            task.setStatus(STATUS_MAP.get(isExistMember));

            String inputMemberTask = objectMapper.writeValueAsString(task);
            kafkaTemplate.send(appProperties.getMemberTopic(),inputMemberTask);
        }catch (Exception e){
            log.error("taskListener Error message = {}",memberTaskMessage,e);
        }
    }

    @KafkaListener(topics ="${kafka.member.task.topic}",groupId = "${kafka.member.task.group}")
    public void removeOrderMemberCheckTaskListener(String removeOrderTaskMessage){
        RemoveOrderTask task = null;
        try{
            task = objectMapper.readValue(removeOrderTaskMessage, RemoveOrderTask.class);
            FindMemberCommand command = FindMemberCommand.builder()
                    .userId(task.getUserId())
                    .build();
            boolean isExistMember = findMemberUseCase.existMember(command);
            String inputTask = objectMapper.writeValueAsString(task);

            if(isExistMember){
                kafkaTemplate.send(appProperties.getRemoveOrderTopic(),inputTask);
            }else{
                kafkaTemplate.send(appProperties.getRemoveRollbackTopic(),"roll back");
            }


        }catch (Exception e){
            log.error("removeOrderMemberCheckTaskListener Error message = {}",removeOrderTaskMessage,e);
        }
    }
}
