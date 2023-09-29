package com.example.adminservice.domain.productentity;

import java.util.List;

public class SizeVo {

    private static final List<Integer> SIZE_LIST = List.of(220,225,230,235,240,245,250,255,260,265,270,275,280,285,290);

    public static List<Integer> getSize(){
      return SIZE_LIST;
    }
}
