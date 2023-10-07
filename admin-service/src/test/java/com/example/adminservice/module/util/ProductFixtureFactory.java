package com.example.adminservice.module.util;

import com.example.adminservice.adapter.out.persistence.product.entity.*;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.util.HashSet;
import java.util.Set;

import static org.jeasy.random.FieldPredicates.*;

public class ProductFixtureFactory {

    public static EasyRandom get(){
        BrandEntity brand = new BrandEntity();
        brand.setId(1L);

        CategoryEntity category = new CategoryEntity();
        category.setId(1L);

        ColorEntity color = new ColorEntity();
        color.setId(1L);
//
//        Set<SizeEntity> sizeEntities = new HashSet<>();
//        SizeEntity size = new SizeEntity();
//        size.setSize(260);
//        size.setQuantity(100);
//        sizeEntities.add(size);
//
//        ProductComponentEntity productComponent = new ProductComponentEntity();
//        productComponent.setColor(color);
//        productComponent.setSizes(sizeEntities);

        var idPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(ProductEntity.class));

        var brandPredicate = named("brand")
                .and(ofType(BrandEntity.class))
                .and(inClass(ProductEntity.class));

        var categroyPredicate = named("category")
                .and(ofType(CategoryEntity.class))
                .and(inClass(ProductEntity.class));

        var productComponentPredicate = named("productComponents")
                .and(ofType(Set.class))
                .and(inClass(ProductEntity.class));

        var params = new EasyRandomParameters()
                .excludeField(idPredicate)
                .randomize(brandPredicate,()->brand)
                .randomize(categroyPredicate,()->category)
                .randomize(productComponentPredicate,()->{
                    Set<ProductComponentEntity> productComponents = new HashSet<>();

                    Set<SizeEntity> sizeEntities = new HashSet<>();
                    SizeEntity size = new SizeEntity();
                    size.setSize(260);
                    size.setQuantity(100);
                    sizeEntities.add(size);

                    ProductComponentEntity productComponent = new ProductComponentEntity();
                    productComponent.setColor(color);
                    productComponent.setSizes(sizeEntities);

                    productComponents.add(productComponent);

                    return productComponents;
                });

        return new EasyRandom(params);


    }

}
