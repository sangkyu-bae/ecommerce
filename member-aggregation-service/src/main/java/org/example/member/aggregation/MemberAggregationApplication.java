package org.example.member.aggregation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"org.example.member.aggregation", "org.example"})
public class MemberAggregationApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberAggregationApplication.class, args);
    }

}