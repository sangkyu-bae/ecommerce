package com.example.adminservice.application.port.out.product;

import com.example.adminservice.adapter.axon.command.ProductCreateCommand;

public interface SendProductTaskPort {

    void sendProductToAxon(ProductCreateCommand command);
}
