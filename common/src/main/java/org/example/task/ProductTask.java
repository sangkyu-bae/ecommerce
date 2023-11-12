package org.example.task;

import lombok.Builder;
import lombok.Data;

@Data
public class ProductTask extends OrderSubTask{

    private long productId;

    private long colorId;

    private int quantity;

    private long sizeId;

    @Builder
    public ProductTask(String subTaskName,
                      Status status,
                      TaskType taskType,
                      long productId,
                      long colorId,
                      long sizeId,
                      int quantity) {
        super(subTaskName, status, taskType);
        this.productId = productId;
        this.colorId = colorId;
        this.quantity = quantity;
        this.sizeId = sizeId;
    }
}
