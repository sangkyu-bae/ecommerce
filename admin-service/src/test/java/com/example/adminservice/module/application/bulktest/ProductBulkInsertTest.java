package com.example.adminservice.module.application.bulktest;

import com.example.adminservice.adapter.out.persistence.product.entity.ProductEntity;
import com.example.adminservice.adapter.out.persistence.repository.SpringDataProductRepository;
import com.example.adminservice.domain.product.service.ProductWriteService;
import com.example.adminservice.module.util.ProductFixtureFactory;
import com.netflix.discovery.converters.Auto;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
public class ProductBulkInsertTest {

   @Autowired
    SpringDataProductRepository productRepository;


   // jdbc bulkInsert가 saveAll 보다 빠름, saveAll은 단건으로 보내는것이 아니라 건건이 보냄
    @Test
    public void bulkInsert(){
        EasyRandom easyRandom = ProductFixtureFactory
                .get();


        List<ProductEntity> productEntityList= IntStream.range(0,10000*10)
                .mapToObj(i -> easyRandom.nextObject(ProductEntity.class))
                .collect(Collectors.toList());

        productRepository.saveAll(productEntityList);
    }

}
