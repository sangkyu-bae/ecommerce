package com.example.adminservice.application.port.out.product;

import com.example.adminservice.adapter.out.kafka.SendProductTask;
import com.example.adminservice.application.port.in.command.FindProductCommand;
import com.example.adminservice.domain.ProductVo;

public interface SendFindProductTaskPort {

    void sendFindProductTask(long productId,String productName);

    void sendFindProductTaskToELK(ProductVo product);
}
