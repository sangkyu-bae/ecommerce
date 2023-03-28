package com.example.demo.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("v1-definition")
                .packagesToExclude("/api/**")
                .build();
    }
    @Bean
    public OpenAPI springShopOpenApi(){
        return new OpenAPI()
                .info(new Info().title("E-commerce Api")
                        .description("kafka 기반 이커머스 프로젝트 API명세서")
                        .version("v0.0.1"));
    }
}
