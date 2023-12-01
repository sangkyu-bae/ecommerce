package org.example.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

//@JsonTypeName(value = "PRODUCT")
@JsonSubTypes.Type(value = ProductTask.class,name = "PRODUCT")
@Data @Builder
@AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(callSuper = true)
public class ProductTask extends OrderSubTask<ProductTask>{
    private long productId;

    private long colorId;

    private int quantity;

    private long sizeId;
//    private Type type;

    public ProductTask(String taskId, String subTaskName,
                       Status status, long productId, long colorId,
                       int quantity, long sizeId){

        this.taskId = taskId;
        this.subTaskName = subTaskName;
        this.status = status;
        this.productId = productId;
        this.colorId = colorId;
        this.quantity = quantity;
        this.sizeId = sizeId;
        this.type = Type.PRODUCT;
    }

    @Override
    @JsonIgnore
    public String getTaskType() {
        return "product";
    }

}
