package com.geotraveljournal.app.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.List;

@Converter(autoApply = true)
public class JsonListConverter implements AttributeConverter<List<Object>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Object> attribute) {
        try {
            if (attribute != null) {
                return objectMapper.writeValueAsString(attribute);
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сериализации списка объектов в JSON", e);
        }
    }

    @Override
    public List<Object> convertToEntityAttribute(String dbData) {
        try {
            if (dbData != null && !dbData.isEmpty()) {
                return objectMapper.readValue(dbData, List.class);
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при десериализации JSON в список объектов", e);
        }
    }
}
