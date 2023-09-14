package com.example.adminservice.domain.product.service;

import com.example.adminservice.domain.color.dto.ColorDto;
import com.example.adminservice.domain.product.dto.ColorProductDto;
import com.example.adminservice.domain.product.dto.ProductDto;
import com.example.adminservice.domain.product.dto.ResponseProductDto;
import com.example.adminservice.domain.product.entity.Product;
import com.example.adminservice.domain.product.repository.ProductRepository;
import com.example.adminservice.domain.size.dto.SizeDto;
import com.example.adminservice.module.common.error.CustomException;
import com.example.adminservice.module.common.error.ErrorCodet;
import com.example.adminservice.domain.brand.dto.BrandDto;
import com.example.adminservice.domain.category.dto.CategoryDto;
import com.example.adminservice.domain.product.dto.ProductSearchDto;
import com.example.adminservice.domain.size.dto.SizeQuantityDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UnknownFormatConversionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProductReadService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    public ProductDto toProductDto(Product product) {
        ProductDto productDto = null;
        try {
            productDto = new ProductDto(product);
        } catch (Exception exception) {
            log.error("ProductDto 변환 실패하였습니다. Error toProductDto()");
            throw new UnknownFormatConversionException("could Not Convert Member to toProductDto");
        }

        return productDto;
    }

    public ResponseProductDto toProductDtos(Product product) {
        ResponseProductDto responseProductDto = null;
        try {
            responseProductDto = modelMapper.map(product,ResponseProductDto.class);
            responseProductDto.setBrand(modelMapper.map(product.getBrand(), BrandDto.class));
            responseProductDto.setCategory(modelMapper.map(product.getCategory(), CategoryDto.class));

            List<ColorProductDto> colorProductDtoList = getColorProductDtoList(product);
            responseProductDto.setColorDataList(colorProductDtoList);
        } catch (Exception exception) {
            log.error("ProductDto 변환 실패하였습니다. Error toProductDto()");
            throw new UnknownFormatConversionException("could Not Convert Member to toProductDto");
        }

        return responseProductDto;
    }

    private List<ColorProductDto> getColorProductDtoList(Product product) {
        List<ColorProductDto> colorProductDtoList= product.getColorProductList().stream()
                .map(colorProduct -> {
                    List<SizeQuantityDto> sizeQuantityDtoList= colorProduct.getSizeList().stream().map(sizeQuantity -> {
                        SizeDto sizeDto = modelMapper.map(sizeQuantity.getSize(),SizeDto.class);
                        return new SizeQuantityDto(sizeDto, sizeQuantity.getQuantity().getQuantity());
                    }).collect(Collectors.toList());

                    ColorDto colorDto = modelMapper.map(colorProduct.getColor(), ColorDto.class);
                    return new ColorProductDto(colorDto, sizeQuantityDtoList);
                }).collect(Collectors.toList());
        return colorProductDtoList;
    }


    public Product readProduct(long productId)  {
        Product product = null;
//        product = productRepository.findById(productId).orElseThrow(() ->
//                new CustomException(ErrorCode.PRODUCT_NOT_FOUND,"readProduct")
//        );

        product = productRepository.findWithBrandAndCategoryAndColorProductById(productId).orElseThrow(() ->
                new CustomException(ErrorCodet.PRODUCT_NOT_FOUND,"readProduct")
        );
        return product;
    }

    public Page<Product> readProduct(Pageable pageable){
        Page<Product> productPage = productRepository.findWithPageByAll(pageable);
        return productPage;
    }

    public ProductSearchDto readProductSearch(Page<Product> productPage){
        List<ResponseProductDto> productDtoList = productPage.stream().map(this::toProductDtos).collect(Collectors.toList());

        ProductSearchDto productSearchDto = ProductSearchDto.builder().
                productList(productDtoList).
                pageNumber(productPage.getNumber()).
                pageSize(productPage.getSize()).
                totalElements(productPage.getTotalElements()).
                build();

        return productSearchDto;
    }

}
