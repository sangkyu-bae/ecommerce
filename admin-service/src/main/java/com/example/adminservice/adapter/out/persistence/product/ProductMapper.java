package com.example.adminservice.adapter.out.persistence.product;

import com.example.adminservice.adapter.in.request.RegisterBrandRequest;
import com.example.adminservice.adapter.in.request.RegisterCategoryRequest;
import com.example.adminservice.adapter.in.request.RegisterColorRequest;
import com.example.adminservice.adapter.in.request.RegisterProductComponentRequest;
import com.example.adminservice.adapter.out.persistence.product.entity.*;
import com.example.adminservice.application.port.in.product.*;
import com.example.adminservice.domain.ProductSearchVo;
import com.example.adminservice.domain.ProductVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductVo mapToDomainEntity(ProductEntity productEntity) {
        ProductVo.ProductBrandVo brand = mapToBrandVo(productEntity.getBrand());
        ProductVo.ProductCategoryVo category = mapToCategoryVo(productEntity.getCategory());
        Set<ProductVo.ProductComponentEntityVo> productComponentEntityVos = mapToProductComponentEntityVo(productEntity);
        return ProductVo.createGenerateProductVo(
                new ProductVo.ProductId(productEntity.getId()),
                new ProductVo.ProductName(productEntity.getName()),
                new ProductVo.ProductPrice(productEntity.getPrice()),
                new ProductVo.ProductDescription(productEntity.getDescription()),
                new ProductVo.ProductImage(productEntity.getProductImage()),
                new ProductVo.ProductAggregateIdentifier(productEntity.getAggregateIdentifier()),
                brand, category, productComponentEntityVos
        );
    }

    public ProductSearchVo mapToDomainEntity(Page<ProductEntity> pagingProduct) {
        List<ProductVo> productVoList = pagingProduct.stream()
                .map(product -> mapToDomainEntity(product)).collect(Collectors.toList());

        return ProductSearchVo.createGenerateProductSearchVo(
                new ProductSearchVo.ProductList(productVoList),
                new ProductSearchVo.PageNumber(pagingProduct.getNumber()),
                new ProductSearchVo.PageSize(pagingProduct.getSize()),
                new ProductSearchVo.TotalElement(pagingProduct.getTotalElements())
        );
    }

    private ProductVo.ProductBrandVo mapToBrandVo(BrandEntity brandEntity) {
        return new ProductVo.ProductBrandVo(brandEntity.getId(), brandEntity.getName());
    }

    private ProductVo.ProductCategoryVo mapToCategoryVo(CategoryEntity category) {
        return new ProductVo.ProductCategoryVo(category.getId(), category.getName());
    }

    public RegisterBrandCommand mapToBrand(RegisterBrandRequest brand) {
        return modelMapper.map(brand, RegisterBrandCommand.class);
    }

    public RegisterCategoryCommand mapToCategory(RegisterCategoryRequest category) {
        return modelMapper.map(category, RegisterCategoryCommand.class);
    }

    public Set<RegisterProductComponentCommand> mapToCommandProductComponents(Set<RegisterProductComponentRequest> productComponents) {
        Set<RegisterProductComponentCommand> componentCommands = new HashSet<>();

        for (RegisterProductComponentRequest registerProductComponentRequest : productComponents) {
            componentCommands.add(mapToProductComponent(registerProductComponentRequest));
        }

        return componentCommands;
    }

    private RegisterProductComponentCommand mapToProductComponent(RegisterProductComponentRequest productComponent) {
        return modelMapper.map(productComponent, RegisterProductComponentCommand.class);
    }

    private Set<ProductVo.ProductComponentEntityVo> mapToProductComponentEntityVo(ProductEntity productEntity) {
        Set<ProductComponentEntity> componentEntities = productEntity.getProductComponents();
        Set<ProductVo.ProductComponentEntityVo> productComponentEntityVos = new HashSet<>();

        for (ProductComponentEntity productComponentEntity : componentEntities) {
            ColorEntity color = productComponentEntity.getColor();
            ProductVo.ProductColorVo productColorVo = new ProductVo.ProductColorVo(color.getId(), color.getName());

            Set<ProductVo.ProductSizeVo> productSizeVos = new HashSet<>();
            for (SizeEntity size : productComponentEntity.getSizes()) {
                ProductVo.ProductSizeVo productSizeVo = ProductVo
                        .ProductSizeVo
                        .readProductSizeVo(size.getId(), size.getSize(), size.getQuantity());
                productSizeVos.add(productSizeVo);
            }
            ProductVo.ProductComponentEntityVo productComponentEntityVo = ProductVo
                    .ProductComponentEntityVo
                    .readProductComponentEntityVo(productComponentEntity.getId(), productColorVo, productSizeVos);
            productComponentEntityVos.add(productComponentEntityVo);
        }

        return productComponentEntityVos;
    }

    public Set<ProductVo.ProductComponentEntityVo> mapToProductComponentEntityVo(RegisterProductCommand command) {
        Set<RegisterProductComponentCommand> productComponentCommands = command.getProductComponents();
        Set<ProductVo.ProductComponentEntityVo> productComponentEntityVos = new HashSet<>();


        for (RegisterProductComponentCommand registerProductCommand : productComponentCommands) {

            RegisterColorRequest registerColorRequest = registerProductCommand.getColor();
            ProductVo.ProductColorVo productColorVo = new ProductVo.ProductColorVo(registerColorRequest.getId(), registerColorRequest.getName());

            Set<ProductVo.ProductSizeVo> productSizeVos = new HashSet<>();
            for (RegisterSizeCommand sizeCommand : registerProductCommand.getSizes()) {
                ProductVo.ProductSizeVo productSizeVo = null;
                if (sizeCommand.getId() > 0) {
                    productSizeVo = ProductVo
                            .ProductSizeVo
                            .updateProductSizeVo(sizeCommand.getId(), sizeCommand.getSize(), sizeCommand.getQuantity());
                } else {
                    productSizeVo = ProductVo
                            .ProductSizeVo
                            .createProductSizeVo(sizeCommand.getSize(), sizeCommand.getQuantity());
                }
                productSizeVos.add(productSizeVo);
            }
            ProductVo.ProductComponentEntityVo productComponentEntityVo = null;
            long componentId = registerProductCommand.getId();

            if (componentId > 0) {
                productComponentEntityVo = ProductVo
                        .ProductComponentEntityVo
                        .updateProductComponentEntityVo(componentId, productColorVo, productSizeVos);
            } else {
                productComponentEntityVo = ProductVo
                        .ProductComponentEntityVo
                        .createProductComponentEntityVo(productColorVo, productSizeVos);
            }

            productComponentEntityVos.add(productComponentEntityVo);
        }

        return productComponentEntityVos;
    }

    public BrandEntity mapToBrand(ProductVo.ProductBrandVo brandVo) {
        return modelMapper.map(brandVo, BrandEntity.class);
    }

    public CategoryEntity mapToCategory(ProductVo.ProductCategoryVo categoryVo) {
        return modelMapper.map(categoryVo, CategoryEntity.class);
    }

    public Set<ProductComponentEntity> mapToProductComponents(Set<ProductVo.ProductComponentEntityVo> productComponents) {
        Set<ProductComponentEntity> productComponentEntities = new HashSet<>();

        for (ProductVo.ProductComponentEntityVo productComponentEntity : productComponents) {
            productComponentEntities.add(mapToProductComponent(productComponentEntity));
        }

        return productComponentEntities;
    }

    private ProductComponentEntity mapToProductComponent(ProductVo.ProductComponentEntityVo productComponentEntityVo) {
        return modelMapper.map(productComponentEntityVo, ProductComponentEntity.class);
    }

    public ProductVo.ProductBrandVo mapToBrand(RegisterBrandCommand registerBrandCommand) {
        return new ProductVo.ProductBrandVo(registerBrandCommand.getId(), registerBrandCommand.getName());
    }

    public ProductVo.ProductCategoryVo mapToCategory(RegisterCategoryCommand registerCategoryCommand) {
        return new ProductVo.ProductCategoryVo(registerCategoryCommand.getId(), registerCategoryCommand.getName());
    }
}
