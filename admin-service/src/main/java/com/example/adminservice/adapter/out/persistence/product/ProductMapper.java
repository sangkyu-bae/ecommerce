package com.example.adminservice.adapter.out.persistence.product;

import com.example.adminservice.adapter.in.web.request.productRequest.RegisterBrandRequest;
import com.example.adminservice.adapter.in.web.request.productRequest.RegisterCategoryRequest;
import com.example.adminservice.adapter.in.web.request.productRequest.RegisterColorRequest;
import com.example.adminservice.adapter.in.web.request.productRequest.RegisterProductComponentRequest;
import com.example.adminservice.adapter.out.persistence.product.entity.*;
import com.example.adminservice.application.port.in.product.*;
import com.example.adminservice.domain.product.dto.ProductVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductVo mapToDomainEntity(ProductEntity productEntity){
        ProductVo.ProductBrandVo brand = mapToBrandVo(productEntity.getBrand());
        ProductVo.ProductCategoryVo category = mapToCategoryVo(productEntity.getCategory());
        Set<ProductVo.ProductComponentEntityVo> productComponentEntityVos = mapToProductComponentEntityVo(productEntity);
        return ProductVo.createGenerateProductVo(
                new ProductVo.ProductId(productEntity.getId()),
                new ProductVo.ProductName(productEntity.getName()),
                new ProductVo.ProductPrice(productEntity.getPrice()),
                new ProductVo.ProductDescription(productEntity.getDescription()),
                new ProductVo.ProductImage(productEntity.getProductImage()),
                brand,category,productComponentEntityVos
        );
    }

    private ProductVo.ProductBrandVo mapToBrandVo(BrandEntity brandEntity){
        return new ProductVo.ProductBrandVo(brandEntity.getId(), brandEntity.getName());
    }

    private ProductVo.ProductCategoryVo mapToCategoryVo(CategoryEntity category){
        return new ProductVo.ProductCategoryVo(category.getId(),category.getName());
    }
    public RegisterBrandCommand mapToBrand(RegisterBrandRequest brand){
        return modelMapper.map(brand, RegisterBrandCommand.class);
    }

    public RegisterCategoryCommand mapToCategory(RegisterCategoryRequest category){
        return modelMapper.map(category, RegisterCategoryCommand.class);
    }

    public Set<RegisterProductComponentCommand> mapToCommandProductComponents(Set<RegisterProductComponentRequest> productComponents){
        Set<RegisterProductComponentCommand> componentCommands = new HashSet<>();

        for(RegisterProductComponentRequest registerProductComponentRequest : productComponents){
            componentCommands.add(mapToProductComponent(registerProductComponentRequest));
        }

        return componentCommands;
    }

    private RegisterProductComponentCommand mapToProductComponent(RegisterProductComponentRequest productComponent){
        return modelMapper.map(productComponent,RegisterProductComponentCommand.class);
    }
    private Set<ProductVo.ProductComponentEntityVo> mapToProductComponentEntityVo(ProductEntity productEntity){
        Set<ProductComponentEntity> componentEntities = productEntity.getProductComponents();
        Set<ProductVo.ProductComponentEntityVo> productComponentEntityVos = new HashSet<>();

        for(ProductComponentEntity productComponentEntity : componentEntities){
            ColorEntity color = productComponentEntity.getColor();
            ProductVo.ProductColorVo productColorVo = new ProductVo.ProductColorVo(color.getId(), color.getName());

            Set<ProductVo.ProductSizeVo> productSizeVos = new HashSet<>();
            for(SizeEntity size : productComponentEntity.getSizes()){
                ProductVo.ProductSizeVo productSizeVo = ProductVo
                        .ProductSizeVo
                        .readProductSizeVo(size.getId(), size.getSize(), size.getQuantity());
                productSizeVos.add(productSizeVo);
            }
            ProductVo.ProductComponentEntityVo productComponentEntityVo = ProductVo
                    .ProductComponentEntityVo
                    .readProductComponentEntityVo(productComponentEntity.getId(),productColorVo,productSizeVos);
            productComponentEntityVos.add(productComponentEntityVo);
        }

        return productComponentEntityVos;
    }

    public Set<ProductVo.ProductComponentEntityVo> mapToProductComponentEntityVo(RegisterProductCommand command){
        Set<RegisterProductComponentCommand> productComponentCommands = command.getProductComponents();
        Set<ProductVo.ProductComponentEntityVo> productComponentEntityVos = new HashSet<>();

        for(RegisterProductComponentCommand registerProductCommand : productComponentCommands){
            RegisterColorRequest registerColorRequest = registerProductCommand.getColor();
            ProductVo.ProductColorVo productColorVo = new ProductVo.ProductColorVo(registerColorRequest.getId(), registerColorRequest.getName());

            Set<ProductVo.ProductSizeVo> productSizeVos = new HashSet<>();
            for(RegisterSizeCommand sizeCommand : registerProductCommand.getSizes()){
                ProductVo.ProductSizeVo productSizeVo = ProductVo
                        .ProductSizeVo
                        .createProductSizeVo(sizeCommand.getSize(),sizeCommand.getQuantity());
                productSizeVos.add(productSizeVo);
            }

            ProductVo.ProductComponentEntityVo productComponentEntityVo = ProductVo
                    .ProductComponentEntityVo
                    .createProductComponentEntityVo(productColorVo,productSizeVos);

            productComponentEntityVos.add(productComponentEntityVo);
        }

        return productComponentEntityVos;
    }

    public BrandEntity mapToBrand(ProductVo.ProductBrandVo brandVo){
        return modelMapper.map(brandVo,BrandEntity.class);
    }

    public CategoryEntity mapToCategory(ProductVo.ProductCategoryVo categoryVo){
        return modelMapper.map(categoryVo,CategoryEntity.class);
    }

    public Set<ProductComponentEntity> mapToProductComponents(Set<ProductVo.ProductComponentEntityVo> productComponents){
        Set<ProductComponentEntity> productComponentEntities = new HashSet<>();

        for(ProductVo.ProductComponentEntityVo productComponentEntity  : productComponents){
            productComponentEntities.add(mapToProductComponent(productComponentEntity));
        }

        return productComponentEntities;
    }

    private ProductComponentEntity mapToProductComponent(ProductVo.ProductComponentEntityVo productComponentEntityVo){
        return modelMapper.map(productComponentEntityVo,ProductComponentEntity.class);
    }
}
