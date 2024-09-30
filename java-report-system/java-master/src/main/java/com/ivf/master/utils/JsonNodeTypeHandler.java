package com.ivf.master.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MyBatis 类型处理器，用于处理 JsonNode 类型的字段。
 * 该处理器将实体类中的 JsonNode 类型字段与数据库中的 JSON 格式字符串进行相互转换。
 *
 * @author CAI
 * @date 2024-02-20
 */
public class JsonNodeTypeHandler implements TypeHandler<JsonNode> {

    // 使用 ObjectMapper 实例来处理 JSON 数据
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 设置 SQL 语句中的参数时，将 JsonNode 对象转换为 JSON 字符串。
     *
     * @param ps        预编译的 SQL 语句
     * @param i         SQL 语句中参数的位置
     * @param parameter 参数值（JsonNode 类型）
     * @param jdbcType  数据库字段的 JDBC 类型
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, JsonNode parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            // 将 JsonNode 转换为字符串，并设置为 SQL 参数
            ps.setString(i, parameter.toString());
        } else {
            // 如果 JsonNode 为 null，则设置 SQL 参数为 NULL
            ps.setNull(i, jdbcType.TYPE_CODE);
        }
    }

    /**
     * 从 ResultSet 中获取数据时，将 JSON 字符串转换为 JsonNode 对象。
     *
     * @param rs         查询结果集
     * @param columnName 列名
     * @return 转换后的 JsonNode 对象
     */
    @Override
    public JsonNode getResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        return parseJson(json);
    }

    /**
     * 从 ResultSet 中获取数据时，将 JSON 字符串转换为 JsonNode 对象。
     *
     * @param rs          查询结果集
     * @param columnIndex 列索引
     * @return 转换后的 JsonNode 对象
     */
    @Override
    public JsonNode getResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        return parseJson(json);
    }

    /**
     * 从 CallableStatement 中获取数据时，将 JSON 字符串转换为 JsonNode 对象。
     *
     * @param cs          可调用语句，用于执行存储过程
     * @param columnIndex 列索引
     * @return 转换后的 JsonNode 对象
     */
    @Override
    public JsonNode getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        return parseJson(json);
    }

    /**
     * 辅助方法，用于将字符串转换为 JsonNode 对象。
     *
     * @param json JSON 格式的字符串
     * @return 转换后的 JsonNode 对象，如果输入为 null 则返回 null
     */
    private JsonNode parseJson(String json) {
        if (json == null) {
            return null;
        }
        try {
            // 使用 ObjectMapper 将字符串解析为 JsonNode
            return objectMapper.readTree(json);
        } catch (Exception e) {
            // 解析失败时抛出运行时异常
            throw new RuntimeException("解析 JSON 错误", e);
        }
    }
}
