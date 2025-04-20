package org.example;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


@Component
@RequiredArgsConstructor
@Slf4j
public class JsonConverter{

    private final ObjectMapper objectMapper;

    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting Map to JSON", e);
        }
    }

    public Map<String, Object> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Map.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading JSON to Map", e);
        }
    }

    public <T> T convert(String json, Class<T> type) {
        try {
            T req = objectMapper.readValue(json, type);
            log.info(req.toString());
            return req;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json parsing error", e);
        }
    }
}
