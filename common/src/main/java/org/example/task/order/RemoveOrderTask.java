package org.example.task.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data @Builder
public class RemoveOrderTask {

    private long productId;

    private long orderId;

    private long userId;

    private long sizeId;

    private int amount;

}
