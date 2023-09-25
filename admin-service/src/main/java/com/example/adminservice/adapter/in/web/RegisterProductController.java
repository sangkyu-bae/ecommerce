package com.example.adminservice.adapter.in.web;

import com.example.adminservice.adapter.in.web.request.productRequest.RegisterProductRequest;
import com.example.adminservice.adapter.out.persistence.product.BrandEntity;
import com.example.adminservice.adapter.out.persistence.product.CategoryEntity;
import com.example.adminservice.adapter.out.persistence.product.ProductComponentEntity;
import com.example.adminservice.adapter.out.persistence.product.ProductMapper;
import com.example.adminservice.application.port.in.product.RegisterBrandCommand;
import com.example.adminservice.application.port.in.product.RegisterCategoryCommand;
import com.example.adminservice.application.port.in.product.RegisterProductCommand;
import com.example.adminservice.application.port.in.RegisterProductUseCase;
import com.example.adminservice.application.port.in.product.RegisterProductComponentCommand;
import com.example.adminservice.application.port.out.RegisterProductPort;
import com.example.adminservice.common.WebAdapter;
import com.example.adminservice.domain.product.dto.ProductVo;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequiredArgsConstructor
@Slf4j
public class RegisterProductController {

    private final RegisterProductUseCase registerProductUseCase;

    private final ProductMapper productMapper;

    private final RegisterProductPort registerProductPort;

    private final ModelMapper modelMapper;

    @Operation(summary = "get posts", description = "지역에 대한 posts들 가져오기")
    @PostMapping("/admin/register/product")
    public ResponseEntity<ProductVo> registerProduct(@RequestBody RegisterProductRequest registerProductRequest) {
        ProductVo productVo = null;
        try {
            String productImage = registerProductRequest.getProductImage();
            productImage = productImage == null ? "" : productImage;

            RegisterBrandCommand registerBrandCommand = productMapper.mapToBrand(registerProductRequest.getBrand());
            RegisterCategoryCommand registerCategoryCommand = productMapper.mapToCategory(registerProductRequest.getCategory());
            Set<RegisterProductComponentCommand> componentCommands = productMapper.mapToCommandProductComponents(registerProductRequest.getProductComponents());

            RegisterProductCommand command = RegisterProductCommand.builder()
                    .brand(registerBrandCommand)
                    .category(registerCategoryCommand)
                    .productComponents(componentCommands)
                    .name(registerProductRequest.getName())
                    .price(registerProductRequest.getPrice())
                    .description(registerProductRequest.getDescription())
                    .productImage(productImage)
                    .build();
            productVo = registerProductUseCase.registerProduct(command);

        } catch (Exception exception) {

        }

        return ResponseEntity.ok().body(productVo);
    }
}
