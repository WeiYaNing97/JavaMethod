package com.example.utils.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Jackson库示例，用于解析JSON字符串并遍历所有字段。
 */
public class JacksonExample {
    public static void main(String[] args) {
        String jsonString = "{\"name\":\"John\", \"age\":30}";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            // 遍历所有字段
            jsonNode.fields().forEachRemaining(entry -> {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}