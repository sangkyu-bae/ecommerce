package com.example.adminservice.infra.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class CategoryCache {
    private final Set<Long> cachedCategoryIds;

    public boolean existsByCategoryId(Long categoryId) {
        return cachedCategoryIds.contains(categoryId);
    }

    public void reloadCategoryIds(Set<Long> newCategoryIds) {
        cachedCategoryIds.clear();
        cachedCategoryIds.addAll(newCategoryIds);
    }
}
