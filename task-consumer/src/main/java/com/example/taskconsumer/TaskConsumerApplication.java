package com.example.taskconsumer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"org.example","com.example.taskconsumer"})
public class TaskConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskConsumerApplication.class,args);
    }
}