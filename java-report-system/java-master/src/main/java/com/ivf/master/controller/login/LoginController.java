package com.ivf.master.controller.login;

import com.ivf.master.dto.LoginRequest;
import com.ivf.master.service.login.LoginService;
import com.ivf.master.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 登录控制器，处理登录和登出的HTTP请求。
 * 使用@RestController标记，表明该类是一个控制器，且返回值为HTTP响应体。
 * 使用@CrossOrigin标记，允许跨域请求，增加了接口的可访问性。
 * 依赖于LoginService来处理具体的登录和登出逻辑。
 * @author CAI
 * @since 2024-02-26
 */
@RestController
@CrossOrigin
public class LoginController {

    // 登录服务实例，通过依赖注入自动装配
    private final LoginService loginService;

    /**
     * 通过构造函数自动装配LoginService。
     * @param loginService 登录服务的实例。
     */
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 处理用户登录请求的端点。
     * 接受一个LoginRequest对象作为请求体，该对象包含用户名和密码。
     * 调用LoginService的login方法进行身份验证，并返回登录结果。
     * @param loginRequest 包含登录信息的请求体，如用户名和密码。
     * @return 包含LoginRequest的ResponseEntity对象，表示登录操作的响应。
     * @author CAI
     * @since 2024-02-26
     */
    @PostMapping("/user/login")
    public ResponseEntity<ApiResponse<LoginRequest>> Login(@RequestBody LoginRequest loginRequest) {
        ResponseEntity<ApiResponse<LoginRequest>> response = loginService.login(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        return ResponseEntity.ok(response.getBody());
    }

    /**
     * 处理用户登出请求的端点。
     * 不需要请求体，表示用户意图登出。
     * 调用LoginService的loginOut方法进行登出操作，并返回登出结果。
     * @return 包含简单字符串消息的ResponseEntity对象，表示登出操作的响应。
     * @author CAI
     * @since 2024-02-26
     */
    @GetMapping("/user/loginOut")
    public ResponseEntity<ApiResponse<String>> LoginOut() {
        ResponseEntity<ApiResponse<String>> response = loginService.loginOut();

        return ResponseEntity.ok(response.getBody());
    }

}
