package org.example.basket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"org.example","org.example.basket"})
public class BasketApplication {
    public static void main(String[] args) {
        SpringApplication.run(BasketApplication.class, args);
    }
}