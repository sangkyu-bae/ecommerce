package com.example.order.application.port.out;

import org.example.task.order.RemoveOrderTask;

public interface SendRemoveOrderTaskPort {

    void removeOrderTask(RemoveOrderTask task);
}
