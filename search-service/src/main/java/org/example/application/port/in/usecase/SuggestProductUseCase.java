package org.example.application.port.in.usecase;

import org.example.application.port.in.command.SuggestCommand;

import java.util.Map;

public interface SuggestProductUseCase {
    Map<String, Object> findSuggestProduct(SuggestCommand command);
}
