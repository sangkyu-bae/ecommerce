package com.example.adminservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.adminservice"})
//@EntityScan(basePackages = {"com.example.adminservice.module.domain", "com.example.adminservice.adapter.out.persistence"})
//@EntityScan(basePackages = "com.example.adminservice.adapter.out.persistence")
@EnableDiscoveryClient
public class AdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminServiceApplication.class, args);
	}

}
