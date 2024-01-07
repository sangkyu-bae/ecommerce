package org.example.ranking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"org.example.ranking", "org.example"})
public class RankingApplication {
    public static void main(String[] args) {
        SpringApplication.run(RankingApplication.class, args);
    }

}