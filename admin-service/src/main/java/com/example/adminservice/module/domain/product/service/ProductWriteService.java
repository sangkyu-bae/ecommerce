package com.example.adminservice.module.domain.product.service;

import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCode;
import com.example.adminservice.module.domain.brand.service.BrandReadService;
import com.example.adminservice.module.domain.category.service.CategoryReadService;
import com.example.adminservice.module.domain.color.entity.Color;
import com.example.adminservice.module.domain.color.repository.ColorRepository;
import com.example.adminservice.module.domain.color.service.ColorReadService;
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
import com.example.adminservice.module.domain.size.repository.SizeRepository;
import com.example.adminservice.module.domain.size.service.SizeReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductWriteService {
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final ProductRepository productRepository;
    private final BrandReadService brandReadService;
    private final CategoryReadService categoryReadService;
    private final ColorReadService colorReadService;
    private final SizeReadService sizeReadService;
    private final QuantityWriteService quantityWriteService;
    private final ColorProductWriteService colorProductWriteService;
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

        product.setBrand(brandReadService.readBrand(createProductDto.getBrandName()));
        product.setCategory(categoryReadService.readCategory(createProductDto.getCategoryName()));

        List<ColorProduct> colorProductList = new ArrayList<>();
        List<ColorDataDto> d= createProductDto.getColorDataList();
        colorProductList = d.stream().map(dd-> test(dd)).collect(Collectors.toList());

        return productRepository.save(product);
    }

    private ColorProduct test(ColorDataDto colorDataDto){
        Color color = colorReadService.readColor(colorDataDto.getColorName());
        List<SizeQuantity> sizeQuantityList= colorDataDto.getSizeAndQuantities().stream()
                .map(sizeAndQuantity-> toSizeQuantity(sizeAndQuantity)).collect(Collectors.toList());

        ColorProduct colorProduct = ColorProduct.builder()
                .color(color)
                .sizeList(sizeQuantityList)
                .build();

        return colorProduct;
    }

    private SizeQuantity toSizeQuantity(SizeAndQuantityDto sizeAndQuantityDto){
        Size size =sizeReadService.readSize(sizeAndQuantityDto.getSize());
        Quantity quantity = Quantity.builder()
                .quantity(sizeAndQuantityDto.getQuantity())
                .build();

        SizeQuantity sizeQuantity = SizeQuantity.builder()
                .quantity(quantity)
                .size(size)
                .build();

        return sizeQuantity;
    }
    private SizeQuantity settingSizeAndQuantity(Size size, int quantity,ColorProduct colorProduct){
        SizeQuantity sizeQuantity = SizeQuantity.builder().
                colorProduct(colorProduct).
                size(size).
                build();
        Quantity saveQuantity = quantityWriteService.createQuantity(quantity,sizeQuantity);

        sizeQuantity.setQuantity(saveQuantity);
        SizeQuantity createSizeQuantity = sizeQuantityRepository.save(sizeQuantity);

        return createSizeQuantity;
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
