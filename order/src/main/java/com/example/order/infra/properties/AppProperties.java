package com.example.order.infra.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("app")
public class AppProperties {
    private String bootstrapServer;

    private String orderTopic;

    private String sendProduct;

    private String consumerProduct;

    private String createOrder;

    private String sendToRemoveOrder;
}
