package org.example.application.port.out;

import java.util.List;

public interface GetProductPort {
    List<String> getProductBrandName(List<Long> productIds);
}
