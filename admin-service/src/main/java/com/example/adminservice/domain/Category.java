package com.example.adminservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Category {

    private Long id;

    private String name;

    public static Category createGenerateCategory(
            CategoryId categoryId,
            CategoryName categoryName) {
        return new Category(
                categoryId.getId(),
                categoryName.getName()
        );
    }

    @Value
    public static class CategoryId {
        Long id;

        public CategoryId(long val) {
            this.id = val;
        }
    }

    @Value
    public static class CategoryName {
        String name;

        public CategoryName(String val) {
            this.name = val;
        }
    }
}
