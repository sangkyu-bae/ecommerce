package org.example.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@JsonSubTypes.Type(value = MemberTask.class,name = "MEMBER")
@Data @Builder
@AllArgsConstructor @NoArgsConstructor
//@EqualsAndHashCode(of = "taskId")
public class MemberTask extends OrderSubTask<MemberTask> {
    private long userId;
//    private Type type;
    public MemberTask(long userId, String taskId,String subTaskName, Status status){
        this.taskId = taskId;
        this.subTaskName = subTaskName;
        this.status = status;
        this.userId = userId;
        this.type = Type.MEMBER;
    }
    @Override
    @JsonIgnore
    public String getTaskType() {
        return "member";
    }


}
