package com.example.adminservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Brand {

    private final Long id;

    private final String name;

    private final LocalDate createAt;

    private final LocalDate updateAt;

    private final String brandImage;


    public static Brand createGenerateBrand(
            BrandId brandId,
            BrandName brandName,
            BrandCreateAt brandCreateAt,
            BrandUpdateAt brandUpdateAt,
            BrandImage brandImage
    ) {
        return new Brand(
                brandId.getId(),
                brandName.getName(),
                brandCreateAt.getCreateAt(),
                brandUpdateAt.getUpdateAt(),
                brandImage.getBrandImage()
        );
    }

    @Value
    public static class BrandId {
        Long id;

        public BrandId(long val) {
            this.id = val;
        }
    }

    @Value
    public static class BrandName {
        String name;

        public BrandName(String val) {
            this.name = val;
        }
    }

    @Value
    public static class BrandCreateAt {
        LocalDate createAt;

        public BrandCreateAt(LocalDate val) {
            this.createAt = val;
        }
    }

    @Value
    public static class BrandUpdateAt {
        LocalDate updateAt;

        public BrandUpdateAt(LocalDate val) {
            this.updateAt = val;
        }
    }

    @Value
    public static class BrandImage {
        String brandImage;

        public BrandImage(String val) {
            this.brandImage = val;
        }
    }
}
