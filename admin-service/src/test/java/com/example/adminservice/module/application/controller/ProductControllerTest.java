package com.example.adminservice.module.application.controller;

import com.example.adminservice.domain.product.dto.ProductDto;
import com.example.adminservice.adapter.out.persistence.Product;
import com.example.adminservice.domain.product.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;

    private static String INITNAME = "init test";
    private static Long ERRORID= Long.valueOf(39248293);
    @BeforeEach
    public void settingProductTest() throws Exception {
        ProductDto productDto = getProductDto(INITNAME);
        Product product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
    }

    @Test
    @DisplayName("상품 등록 - 입력값 정상")
    void createProduct() throws Exception {
        String name = "test Product";
        ProductDto productDto = getProductDto(name);
        mockMvc.perform(post("/admin/product")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-User-Id", "uiwv29l@naver.com")
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andDo(print());

        Product product = productRepository.findByName(name);
        assertEquals(productDto.getName(), name);
    }

    @Test
    @DisplayName("상품 등록 - 입력값 비정상")
    void errorCreateProduct() throws Exception {
        String name = "";
        ProductDto productDto = getProductDto(name);
        mockMvc.perform(post("/admin/product")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .header("X-User-Id", "uiwv29l@naver.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().is5xxServerError());
        assertEquals(productRepository.existsByName(name), false);
    }

    @Test
    @DisplayName("상품 삭제 - 입력값 정상")
    void removeProduct()throws Exception{
        Long productId = getProductId();
        mockMvc.perform(delete("/admin/"+productId)
                        .header("X-User-Id", "uiwv29l@naver.com"))
                .andExpect(status().isOk());

        assertEquals(productRepository.existsById(productId),false);
    }

    @Test
    @DisplayName("상품 삭제 - 입력값 비정상")
    void errorRemoveProduct() throws Exception{
        mockMvc.perform(delete("/admin/"+ERRORID)
                        .header("X-User-Id", "uiwv29l@naver.com"))
                .andExpect(status().isNotFound());

        assertEquals(productRepository.existsById(ERRORID),false);
    }

    @Test
    @DisplayName("특정 상품 가져오기 - 입력값 정상")
    void readProduct() throws Exception{
        Long productId = getProductId();

        mockMvc.perform(get("/admin/"+productId)
                        .header("X-User-Id", "uiwv29l@naver.com"))
                .andExpect(status().isOk());

        assertEquals(productRepository.existsById(productId),true);
    }

    @Test
    @DisplayName("특정 상품 가져오기 - 입력값 비정상")
    void errorReadProduct() throws Exception{
        mockMvc.perform(get("/admin/"+ERRORID)
                        .header("X-User-Id", "uiwv29l@naver.com"))
                .andExpect(status().isNotFound());

        assertEquals(productRepository.existsById(ERRORID),false);
    }

    @Test
    @DisplayName("상품 수정 하기 - 입력값 정상")
    void updateProduct() throws Exception{
        String updateName="updateTest";
        ProductDto updateProductDto = getProductDto(updateName);
        Long productId = getProductId();

        mockMvc.perform(put("/admin/"+productId)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .header("X-User-Id", "uiwv29l@naver.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateProductDto)))
                .andExpect(status().isOk());

        Product product = productRepository.findByName(updateName);
        assertEquals(updateName,product.getName());
    }

    @Test
    @DisplayName("상품 수정 하기 - 입력값 비정상")
    void errorUpdateProduct() throws Exception{
        String errorUpdateName = "t";
        ProductDto productDto = getProductDto(errorUpdateName);
        Long productId =getProductId();

        mockMvc.perform(put("/admin/"+productId)
                .characterEncoding(StandardCharsets.UTF_8)
                .header("X-User-Id", "uiwv29l@naver.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isBadRequest());

        assertEquals(productRepository.existsByName(errorUpdateName),false);

        String updateName = "testUpdate";
        ProductDto nomalProductDto = getProductDto(updateName);

        mockMvc.perform(put("/admin/"+ERRORID)
                .characterEncoding(StandardCharsets.UTF_8)
                .header("X-User-Id", "uiwv29l@naver.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nomalProductDto)))
                .andExpect(status().isNotFound());

        assertEquals(productRepository.existsByName(updateName),false);
    }


    private ProductDto getProductDto(String name) {
        ProductDto productDto = ProductDto.builder()
                .name(name)
                .price(14000)
                .description("테스트상품 등록")
                .build();
        return productDto;
    }
    private Long getProductId() {
        Product product = productRepository.findByName(INITNAME);
        Long productId= product.getId();
        return productId;
    }


}