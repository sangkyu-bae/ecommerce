package com.example.adminservice.adapter.in.web;

import com.example.adminservice.adapter.out.persistence.ProductComponent;
import com.example.adminservice.adapter.out.persistence.SizeEntity;
import com.example.adminservice.domain.brand.entity.Brand;
import com.example.adminservice.domain.category.entity.Category;
import com.example.adminservice.domain.product.dto.ColorDataDto;
import com.example.adminservice.domain.product.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProductRequest {

    private String name;

    private int price;

    private String description;

    private String productImage;

    private Brand brand;

    private Category category;

    private Set<ProductComponent> productComponents;


}
