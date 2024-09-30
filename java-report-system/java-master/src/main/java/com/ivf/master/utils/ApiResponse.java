package com.ivf.master.utils;


import lombok.Data;

/**
 * 通用的 API 响应对象，用于统一格式返回数据
 *
 * @param <T> 数据的泛型类型
 * @author CAI
 * @since 2024-01-08
 */
@Data
public class ApiResponse<T> {
    private int code;     // 响应状态码
    private String message; // 响应消息
    private T data;         // 响应数据

    /**
     * 构造函数，用于创建 ApiResponse 对象
     *
     * @param code 响应状态码
     * @param message  响应消息
     * @param data 响应数据
     */
    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 返回状态码，代表请求成功
    public static final int SUCCESS = 0;

    // 返回状态码，代表资源已创建
    public static final int RESOURCE_CREATED = 201;

    // 返回状态码，代表请求失败
    public static final int FAILURE = 400;

    // 返回状态码，代表未经授权
    public static final int UNAUTHORIZED = 401;

    // 返回状态码，代表服务器拒绝处理请求
    public static final int FORBIDDEN = 403;

    // 返回状态码，代表资源未找到
    public static final int NOT_FOUND = 404;

    // 返回状态码，代表服务器内部错误
    public static final int INTERNAL_SERVER_ERROR = 500;

    // 返回状态码，代表服务不可用
    public static final int SERVICE_UNAVAILABLE = 503;
}
