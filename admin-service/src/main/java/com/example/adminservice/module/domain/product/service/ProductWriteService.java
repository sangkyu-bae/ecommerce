package com.example.adminservice.module.domain.product.service;

import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCode;
import com.example.adminservice.module.domain.brand.service.BrandReadService;
import com.example.adminservice.module.domain.category.service.CategoryReadService;
import com.example.adminservice.module.domain.color.entity.Color;
import com.example.adminservice.module.domain.color.service.ColorReadService;
import com.example.adminservice.module.domain.product.dto.ProductDto;
import com.example.adminservice.module.domain.product.entity.ColorProduct;
import com.example.adminservice.module.domain.product.entity.Product;
import com.example.adminservice.module.domain.product.repository.ProductRepository;
import com.example.adminservice.module.domain.quantity.entity.Quantity;
import com.example.adminservice.module.domain.quantity.service.QuantityWriteService;
import com.example.adminservice.module.domain.size.entity.Size;
import com.example.adminservice.module.domain.size.entity.SizeQuantity;
import com.example.adminservice.module.domain.size.repository.SizeRepository;
import com.example.adminservice.module.domain.size.service.SizeReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductWriteService {
    private final SizeRepository sizeRepository;
    private final ProductRepository productRepository;
    private final BrandReadService brandReadService;
    private final CategoryReadService categoryReadService;
    private final ColorReadService colorReadService;
    private final SizeReadService sizeReadService;
    private final QuantityWriteService quantityWriteService;
    private final ColorProductWriteService colorProductWriteService;
    private final ModelMapper modelMapper;

    public Product createProduct(ProductDto productDto) {
        Product product = saveNewProduct(productDto);
        return product;
    }

    private Product saveNewProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        product.setCreateAt(LocalDate.now());
        product.setUpdateAt(LocalDate.now());

        product.setBrand(brandReadService.readBrand(productDto.getBrandName()));
        product.setCategory(categoryReadService.readCategory(productDto.getCategoryName()));

        Size size = sizeReadService.readSize(productDto.getSize());
        SizeQuantity sizeQuantity = settingSizeAndQuantity(size,productDto.getQuantity());

        Color color = colorReadService.readColor(productDto.getColorName());
        ColorProduct colorProduct = colorProductWriteService.createColorProduct(color, sizeQuantity, product);

        product.setColorProductList(List.of(colorProduct));

        return productRepository.save(product);
    }

    private SizeQuantity settingSizeAndQuantity(Size size, int quantity){
        Quantity saveQuantity = quantityWriteService.createQuantity(quantity);
        SizeQuantity sizeQuantity = SizeQuantity.builder().
                quantity(saveQuantity).
                size(size).
                build();

        return sizeQuantity;
    }

    public void removeProduct(long productId) {
        if (!productRepository.existsById(productId)) {
            throw new CustomException(ErrorCode.PRODUCT_NOT_FOUND,"removeProduct");
        }
        productRepository.deleteById(productId);
    }

    public Product updateProduct(Product product, ProductDto updateProductDto) {
        product.setUpdateAt(LocalDate.now());
        modelMapper.map(updateProductDto, product);
        return product;
    }

}
