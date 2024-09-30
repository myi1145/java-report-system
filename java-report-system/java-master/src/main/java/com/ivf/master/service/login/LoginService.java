package com.ivf.master.service.login;

import com.ivf.master.common.DataSourceContextHolder;
import com.ivf.master.dto.LoginRequest;
import com.ivf.master.mapper.login.LoginMapper;
import com.ivf.master.utils.ApiResponse;
import com.ivf.master.utils.ECBCryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 登录服务，负责处理登录逻辑。
 * 使用@Service注解，将该类标记为Spring框架的服务类。
 *
 * @author CAI
 * @since 2024-02-26
 */
@Service
public class LoginService {

    // 登录模块的Mapper，用于数据库操作
    private final LoginMapper loginMapper;

    /**
     * 通过构造函数自动注入LoginMapper。
     *
     * @param loginMapper 登录模块的Mapper，用于数据库访问。
     */
    @Autowired
    public LoginService(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    /**
     * 用户登录服务方法。
     * 验证用户名和密码，如果验证成功，返回用户信息和权限。
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果的响应实体，包含登录成功或失败的详细信息
     * @author CAI
     * @since 2024-02-26
     */
    public ResponseEntity<ApiResponse<LoginRequest>> login(String username, String password) {
        try {
            // 设置当前线程的数据源为MySQL
            DataSourceContextHolder.setDataSource("mysql");

            ApiResponse<LoginRequest> errorResponse;

            // 检查用户是否存在于数据库中
            int isExist = loginMapper.SelectUser(username);
            if (isExist == 0) {  // 用户不存在
                errorResponse = new ApiResponse<>(
                        ApiResponse.NOT_FOUND,
                        "未存在该用户！",
                        null
                );
                return ResponseEntity.ok(errorResponse);
            }

            // 获取用户密码并进行解密比对
            Map<String, Object> userData = loginMapper.GetPassword(username);
            String emPassword = (String) userData.get("PASSWORD");
            String name = (String) userData.get("NAME");
            // 对密码进解密
            String decPassword = ECBCryptUtils.decrypt(emPassword);

            assert decPassword != null;
            // 比对用户输入的密码与数据库中的密码
            if (!decPassword.equals(password)) {  // 密码不匹配
                errorResponse = new ApiResponse<>(
                        ApiResponse.FORBIDDEN,
                        "密码错误！",
                        null
                );
                return ResponseEntity.ok(errorResponse);
            }

            // 密码验证通过，构建成功响应
            ApiResponse<LoginRequest> successResponse = getResponse(username, password, name);
            return ResponseEntity.ok(successResponse);

        } catch (Exception e) {
            // 捕获异常并生成错误消息
            String errorMessage = "发生异常: " + e.getMessage();
            ApiResponse<LoginRequest> errorResponse = new ApiResponse<>(
                    ApiResponse.FAILURE,
                    errorMessage,
                    null // 设置数据为空或者其他默认值
            );

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    /**
     * 构建登录成功后的响应信息。
     *
     * @param username 用户名
     * @param password 密码
     * @return 包含用户信息和权限的响应实体
     * @author CAI
     * @since 2024-02-26
     */
    private static ApiResponse<LoginRequest> getResponse(String username, String password, String name) {
        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        loginRequest.setName(name);
        loginRequest.setRole(username);
        loginRequest.setRoleId(username);
        loginRequest.setPermissions(new String[]{"*.*.*"});

        return new ApiResponse<>(
                ApiResponse.SUCCESS,
                "success",
                loginRequest
        );
    }

    /**
     * 用户登出服务方法。
     * 目前该方法仅返回一个成功的响应，实际逻辑根据需求实现。
     *
     * @return 表示登出成功的响应实体。
     */
    public ResponseEntity<ApiResponse<String>> loginOut() {
        return ResponseEntity.badRequest().body(new ApiResponse<>(
                ApiResponse.SUCCESS,
                "success",
                null
        ));
    }

}
