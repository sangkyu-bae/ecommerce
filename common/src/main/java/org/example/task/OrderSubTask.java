package org.example.task;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type",visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductTask.class,name = "PRODUCT"),
        @JsonSubTypes.Type(value = MemberTask.class, name = "MEMBER")
})
@Data
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public abstract class OrderSubTask<T> {
    protected String taskId;
    protected String subTaskName;

    protected Status status;

    protected Type type;

    abstract public String getTaskType();
    public static enum Status{
        READY,SUCCESS,FAIL
    }

    public static enum Type{
        MEMBER,PRODUCT
    }
}
