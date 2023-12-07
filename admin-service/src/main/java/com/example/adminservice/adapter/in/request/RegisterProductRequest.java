package com.example.adminservice.adapter.in.request;

//import com.example.adminservice.adapter.out.persistence.product.entity.SizeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

        import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProductRequest {

    private String name;

    private int price;

    private String description;

    private String productImage;

    private RegisterBrandRequest brand;

    private RegisterCategoryRequest category;

    private Set<RegisterProductComponentRequest> productComponents;

}


