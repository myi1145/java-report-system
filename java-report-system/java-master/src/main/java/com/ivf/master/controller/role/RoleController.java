package com.ivf.master.controller.role;

import com.ivf.master.service.role.RoleService;
import com.ivf.master.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 角色控制器，用于处理前端对角色数据的请求。
 * 通过@RestController注解，表明该类是一个REST控制器，返回的数据自动转换为JSON。
 * @CrossOrigin 注解允许跨域请求，便于前后端分离开发。
 * @author CAI
 * @since 2024-02-26
 */
@RestController
@CrossOrigin
public class RoleController {
    private final RoleService roleService;  // 注入角色服务类，用于业务逻辑处理

    /**
     * 通过构造函数自动注入RoleService。
     * @param roleService 角色服务类实例。
     * @author CAI
     * @since 2024-02-26
     */
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 根据角色名获取角色权限列表。
     * 通过@GetMapping注解，定义了处理GET请求的方法，URL路径为"/role/list"。
     * 使用@RequestParam注解获取URL中的"roleName"参数。
     *
     * @param roleName 请求参数，角色名称，用于查询角色权限列表。
     * @return 返回包含角色权限列表的响应实体。列表中的每个元素都是一个包含菜单详细信息的Map对象。
     * @author CAI
     * @since 2024-02-26
     */
    @GetMapping("/role/list")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getRoleList(@RequestParam String roleName) {
        ResponseEntity<ApiResponse<List<Map<String, Object>>>> response = roleService.getRoleList(roleName);
        return ResponseEntity.ok(response.getBody());
    }
}
