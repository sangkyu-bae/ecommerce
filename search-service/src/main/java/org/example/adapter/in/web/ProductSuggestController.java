package org.example.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.example.application.port.in.command.SuggestCommand;
import org.example.application.port.in.usecase.SuggestProductUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.example.WebAdapter;

import java.util.Map;
import java.util.Objects;

@RestController
@WebAdapter
@RequiredArgsConstructor
public class ProductSuggestController {

    private final SuggestProductUseCase suggestProductUseCase;

    @GetMapping("/search/suggest")
    public ResponseEntity<?> suggestBrandsAndProducts(@RequestHeader("X-User-Id") Long userId){

        SuggestCommand command = SuggestCommand.builder()
                .userId(userId)
                .build();


        Map<String, Object> map = suggestProductUseCase.findSuggestProduct(command);
        return null;
    }

}