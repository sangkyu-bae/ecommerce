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

    private String memberId;

    private String subTaskName;

    private String taskType;

    private String status;

    private TaskType task;
    public static enum TaskType{
        MEMEBER,DELIVERY
    }
}
