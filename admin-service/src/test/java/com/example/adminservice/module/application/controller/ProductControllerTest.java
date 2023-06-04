package com.example.adminservice.module.application.controller;

import com.example.adminservice.module.application.usecase.ProductUseCase;
import com.example.adminservice.module.domain.product.dto.ProductDto;
import com.example.adminservice.module.domain.product.entity.Product;
import com.example.adminservice.module.domain.product.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
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
    @Value("${token.secret}")
    private String SECRET;
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("상품 등록 - 입력값 정상")
    void createProduct() throws Exception {
        String name="test Product";
        ProductDto productDto= ProductDto.builder()
                .name("test Product")
                .price(14000)
                .description("테스트상품 등록")
                .build();
        mockMvc.perform(post("/admin/product")
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andDo(print());

        Product product = productRepository.findByName(name);
        assertEquals(productDto.getName(),name);
    }
    



}