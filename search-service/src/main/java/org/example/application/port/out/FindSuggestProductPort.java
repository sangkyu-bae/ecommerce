package org.example.application.port.out;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface FindSuggestProductPort {

    Map<String, Objects> findSuggestProduct(List<String> productNameList);
}
