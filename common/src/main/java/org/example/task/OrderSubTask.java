package org.example.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSubTask {

    private String taskId;

    private String subTaskName;

    private Status status;

    private TaskType taskType;
    public static enum TaskType{
        MEMBER, PRODUCT
    }

    public static enum Status{
        READY,SUCCESS,FAIL
    }
}
