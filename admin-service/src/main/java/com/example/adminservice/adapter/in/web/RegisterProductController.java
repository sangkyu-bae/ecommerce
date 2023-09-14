package com.example.adminservice.adapter.in.web;

import com.example.adminservice.application.port.in.RegisterProductCommand;
import com.example.adminservice.application.port.in.RegisterProductUseCase;
import com.example.adminservice.common.WebAdapter;
import com.example.adminservice.domain.product.dto.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@Slf4j
public class RegisterProductController {

    private final RegisterProductUseCase registerProductUseCase;

    @PostMapping("/admin/register/product")
    ResponseEntity<ProductVo> registerProduct(@RequestBody RegisterProductRequest registerProductRequest){
        ProductVo productVo = null;
        try{
            String productImage = registerProductRequest.getProductImage();
            productImage = productImage == null ? "": productImage;

            RegisterProductCommand command = RegisterProductCommand.builder()
                    .productComponents(registerProductRequest.getProductComponents())
                    .brand(registerProductRequest.getBrand())
                    .category(registerProductRequest.getCategory())
                    .name(registerProductRequest.getName())
                    .productImage(productImage)
                    .description(registerProductRequest.getDescription())
                    .price(registerProductRequest.getPrice())
                    .build();
            productVo = registerProductUseCase.registerProduct(command);
        }catch (Exception exception){

        }

        return ResponseEntity.ok().body(productVo);
    }
}
