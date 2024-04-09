package com.example.adminservice.application.service.size;

import com.example.adminservice.application.port.in.usecase.size.FindSizeUseCase;
import com.example.adminservice.domain.SizeCheck;
import com.example.adminservice.domain.SizeVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.UseCase;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class FindSizeService implements FindSizeUseCase {

    @Override
    public List<SizeVo> findSizeAll() {

        List<Integer> sizeList = SizeCheck.getSize();

        List<SizeVo> sizeVos = new ArrayList<>();
        for(int i = 0; i < sizeList.size() ;i++){
            SizeVo sizeVo = SizeVo.createGenerateSizeVo(
                    new SizeVo.SizeId(i+1),
                    new SizeVo.Size(sizeList.get(i)),
                    new SizeVo.Quantity(0)
            );

            sizeVos.add(sizeVo);
        }

        return sizeVos;
    }
}
