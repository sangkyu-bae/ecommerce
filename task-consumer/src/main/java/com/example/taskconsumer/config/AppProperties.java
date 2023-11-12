package com.example.taskconsumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("app")
public class AppProperties {
    private String bootstrapServer;
    private String memberTopic;
    private String productTopic;
    private String orderResultTopic;
}
