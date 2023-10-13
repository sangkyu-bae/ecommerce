package com.example.adminservice.infra.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("app")
public class AppProperties {
    private String bootstrapServer;
    private String orderTopic;
    private String createDelivery;
}
