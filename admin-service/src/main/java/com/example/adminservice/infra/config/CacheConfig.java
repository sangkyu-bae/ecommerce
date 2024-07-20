package com.example.adminservice.infra.config;

import com.example.adminservice.adapter.out.persistence.entity.CategoryEntity;
import com.example.adminservice.adapter.out.persistence.repository.CategoryEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Configuration
public class CacheConfig {

    private final CategoryEntityRepository categoryEntityRepository;
    private Set<Long> cachedCategoryIds;

    public CacheConfig(CategoryEntityRepository categoryEntityRepository) {
        this.categoryEntityRepository = categoryEntityRepository;
    }

    @PostConstruct
    public void init() {
        this.cachedCategoryIds = categoryEntityRepository.findAll()
                .stream()
                .map(CategoryEntity::getId)
                .collect(Collectors.toSet());
    }

    @Bean
    public Set<Long> cachedCategoryIds() {
        return this.cachedCategoryIds;
    }
}