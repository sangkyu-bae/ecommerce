package com.example.adminservice.application.port.out.brand;

public interface SendFindProductTaskPort {

    void sendFindProductTask(long productId,String productName);
}
