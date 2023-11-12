package org.example.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderTask {

    private String taskId;

    private String taskName;

    private List<OrderSubTask> subTaskList;
}
