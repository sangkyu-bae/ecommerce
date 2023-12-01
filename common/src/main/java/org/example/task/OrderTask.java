package org.example.task;

import lombok.*;

import java.util.List;


@NoArgsConstructor @Data
@Builder
public class OrderTask {

    private String taskId;

    private String taskName;

    private List<OrderSubTask> subTaskList;
    public OrderTask(String taskId, String taskName, List<OrderSubTask> subTaskList){
        this.taskId = taskId;
        this.taskName = taskName;
        this.subTaskList = subTaskList;
    }
}
