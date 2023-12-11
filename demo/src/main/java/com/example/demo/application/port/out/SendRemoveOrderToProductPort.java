package com.example.demo.application.port.out;

import org.example.task.order.RemoveOrderTask;

public interface SendRemoveOrderToProductPort {

    void removeOrderTask(RemoveOrderTask task);
}
