package com.example.adminservice.module.domain.product.service;

import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCode;
import com.example.adminservice.module.domain.product.dto.ColorDataDto;
import com.example.adminservice.module.domain.product.dto.CreateProductDto;
import com.example.adminservice.module.domain.product.dto.ProductDto;
import com.example.adminservice.module.domain.product.dto.SizeAndQuantityDto;
import com.example.adminservice.module.domain.product.entity.ColorProduct;
import com.example.adminservice.module.domain.product.entity.Product;
import com.example.adminservice.module.domain.product.repository.ProductRepository;
import com.example.adminservice.module.domain.quantity.entity.Quantity;
import com.example.adminservice.module.domain.quantity.service.QuantityWriteService;
import com.example.adminservice.module.domain.size.entity.Size;
import com.example.adminservice.module.domain.size.entity.SizeQuantity;
import com.example.adminservice.module.domain.size.repository.SizeQuantityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductWriteService {
    private final ProductRepository productRepository;
    private final QuantityWriteService quantityWriteService;
    private final SizeQuantityRepository sizeQuantityRepository;
    private final ModelMapper modelMapper;

    public Product createProduct(CreateProductDto productDto) {
        Product product = saveNewProduct(productDto);
        return product;
    }

    private Product saveNewProduct(CreateProductDto createProductDto) {
        Product product = modelMapper.map(createProductDto, Product.class);
        product.setCreateAt(LocalDate.now());
        product.setUpdateAt(LocalDate.now());

        toColorProduct(createProductDto,product);

        return productRepository.save(product);
    }

    private void toColorProduct(CreateProductDto productDto, Product product){
        List<ColorDataDto> dataList= productDto.getColorDataList();
        List<ColorProduct> colorProductList  = dataList.stream()
                .map(data-> setColorProductData(data,product)).collect(Collectors.toList());

        product.addColorProductAll(colorProductList);
    }

    private ColorProduct setColorProductData(ColorDataDto colorDataDto, Product product){
        ColorProduct colorProduct = ColorProduct.builder()
                .color(colorDataDto.getColorName())
                .product(product)
                .build();

        Set<SizeQuantity> sizeQuantityList= colorDataDto.getColorSize().stream()
                .map(sizeAndQuantity-> toSizeQuantity(sizeAndQuantity,colorProduct)).collect(Collectors.toSet());

        colorProduct.setSizeList(sizeQuantityList);
        return colorProduct;
    }

    private SizeQuantity toSizeQuantity(SizeAndQuantityDto sizeAndQuantityDto,ColorProduct colorProduct){
        Quantity quantity = quantityWriteService.creatQuantity(sizeAndQuantityDto.getQuantity());

        SizeQuantity sizeQuantity = SizeQuantity.builder()
                .quantity(quantity)
                .size(sizeAndQuantityDto.getSize())
                .colorProduct(colorProduct)
                .build();

        return sizeQuantity;
    }

    public void removeProduct(long productId) {
        if (!productRepository.existsById(productId)) {
            throw new CustomException(ErrorCode.PRODUCT_NOT_FOUND,"removeProduct");
        }
        productRepository.deleteById(productId);
    }

    public Product updateProduct(Product product, CreateProductDto updateProductDto) {
        product.setUpdateAt(LocalDate.now());
        modelMapper.map(updateProductDto, product);

        toColorProduct(updateProductDto,product);
        return product;
    }

}
