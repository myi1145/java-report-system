package com.ivf.master.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * JPA属性转换器，用于将JsonNode类型的实体属性自动转换为数据库列中存储的JSON字符串，
 * 并且在从数据库查询数据时，将JSON字符串转换回JsonNode类型。
 *
 * @author CAI
 * @date 2024-02-20
 */
@Converter(autoApply = true)
public class JsonNodeConverter implements AttributeConverter<JsonNode, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();  // 创建ObjectMapper实例用于JSON处理

    /**
     * 将实体属性（JsonNode类型）转换为用于存储在数据库中的字符串。
     *
     * @param attribute 实体属性值（JsonNode类型）
     * @return 一个代表JSON的字符串，如果属性为null则返回null
     */
    @Override
    public String convertToDatabaseColumn(JsonNode attribute) {
        if (attribute == null) {
            return null; // 如果属性为null，直接返回null
        }

        try {
            return objectMapper.writeValueAsString(attribute);  // 将JsonNode转换为字符串
        } catch (JsonProcessingException e) {
            throw new RuntimeException("将JsonNode转换为字符串时出错", e);  // 转换失败抛出运行时异常
        }
    }

    /**
     * 将数据库列的字符串（代表JSON）转换回实体属性（JsonNode类型）。
     *
     * @param dbData 数据库列中存储的字符串
     * @return 转换后的JsonNode对象，如果字符串为null则返回null
     */
    @Override
    public JsonNode convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;  // 如果数据库数据为null，直接返回null
        }

        try {
            return objectMapper.readTree(dbData);  // 将字符串转换回JsonNode
        } catch (JsonProcessingException e) {
            throw new RuntimeException("将字符串转换为JsonNode时出错", e);  // 转换失败抛出运行时异常
        }
    }
}
