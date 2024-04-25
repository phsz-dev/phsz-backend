package com.phsz.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class JsonbConverter implements AttributeConverter<JsonNode, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(JsonNode attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            String tmp = objectMapper.writeValueAsString(attribute);
            System.out.println("tmp: " + tmp);
            return tmp;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not convert json to string", e);
        }
    }

    @Override
    public JsonNode convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return objectMapper.readTree(dbData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not convert string to json", e);
        }
    }
}
