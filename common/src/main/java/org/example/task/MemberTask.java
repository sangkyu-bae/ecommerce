package org.example.task;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
public class MemberTask extends OrderSubTask {
    private String memberEmail;

    @Builder
    public MemberTask(String taskId,String subTaskName, Status status, TaskType taskType,String memberEmail) {
        super(taskId,subTaskName, status, taskType);
        this.memberEmail = memberEmail;
    }

}
