package org.example.application.port.out;

import org.example.domain.TopProduct;

import java.util.List;

public interface FindSuggestProductPort {

    List<TopProduct> findSuggestProduct(List<String> productNameList);
}
