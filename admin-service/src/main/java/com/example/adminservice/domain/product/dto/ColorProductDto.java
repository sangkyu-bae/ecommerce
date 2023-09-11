package com.example.adminservice.domain.product.dto;

import com.example.adminservice.domain.color.dto.ColorDto;
import com.example.adminservice.domain.size.dto.SizeQuantityDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
