package com.example.taskconsumer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TaskConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskConsumerApplication.class,args);
    }
}