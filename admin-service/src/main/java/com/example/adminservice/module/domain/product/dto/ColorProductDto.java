package com.example.adminservice.module.domain.product.dto;

import com.example.adminservice.module.domain.color.dto.ColorDto;
import com.example.adminservice.module.domain.color.entity.Color;
import com.example.adminservice.module.domain.product.entity.ColorProduct;
import com.example.adminservice.module.domain.size.dto.SizeQuantityDto;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@Getter @Setter
public class ColorProductDto {
   private ColorDto colorDto;

   List<SizeQuantityDto> sizeQuantityDtoList;

   public ColorProductDto(ColorDto colorDto, List<SizeQuantityDto> sizeQuantityDto){
      this.colorDto = colorDto;
      this.sizeQuantityDtoList = sizeQuantityDto;
   }
}
