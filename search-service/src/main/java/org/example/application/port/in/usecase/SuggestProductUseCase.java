package org.example.application.port.in.usecase;

import org.example.application.port.in.command.SuggestCommand;
import org.example.domain.TopProduct;

import java.util.List;
import java.util.Map;

public interface SuggestProductUseCase {
    List<TopProduct> findSuggestProduct(SuggestCommand command);
}
