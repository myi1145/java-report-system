package com.ivf.master.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用于处理登录请求的数据传输对象（DTO）。
 * 包含了用于验证登录的用户名和密码字段。
 */
@Data
public class LoginRequest {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String name;

    private String role;

    private String roleId;

    private String[] permissions;
}
