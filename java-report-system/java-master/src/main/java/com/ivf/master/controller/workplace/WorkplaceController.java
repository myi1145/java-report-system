package com.ivf.master.controller.workplace;

import com.ivf.master.service.workplace.WorkplaceService;
import com.ivf.master.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Workplace控制器，处理前端关于Workplace页面的请求。
 * 提供统计数据、项目信息、动态、团队信息以及指数等数据的接口。
 * 使用@RestController注解，表明该类是一个控制器，返回的数据自动转换为JSON格式。
 * @CrossOrigin 注解允许跨域请求，便于前后端分离的开发模式。
 */
@RestController
@CrossOrigin
public class WorkplaceController {

    private final WorkplaceService workplaceService;

    /**
     * 通过构造函数自动注入WorkplaceService。
     * @param workplaceService workplace服务类，提供数据获取的业务逻辑。
     */
    @Autowired
    public WorkplaceController(WorkplaceService workplaceService) {
        this.workplaceService = workplaceService;
    }

    /**
     * 获取统计数据的接口。
     * 调用workplaceService的getTotal方法获取统计数据，并返回给前端。
     *
     * @return 统计数据的响应实体。
     * @author CAI
     * @since 2024-02-26
     */
    @GetMapping("workplace/total")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getTotal() {

        ResponseEntity<ApiResponse<Map<String, Object>>> response = workplaceService.getTotal();

        return ResponseEntity.ok(response.getBody());
    }

    /**
     * 获取项目信息的接口。
     * 调用workplaceService的getProject方法获取项目信息，并返回给前端。
     *
     * @return 项目信息的响应实体，包含多个项目的列表信息。
     * @author CAI
     * @since 2024-02-26
     */
    @GetMapping("workplace/project")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getProject() {

        ResponseEntity<ApiResponse<List<Map<String, Object>>>> response = workplaceService.getProject();

        return ResponseEntity.ok(response.getBody());
    }

    /**
     * 获取动态信息的接口。
     * 调用workplaceService的getDynamic方法获取动态信息，并返回给前端。
     *
     * @return 动态信息的响应实体，包含多条动态信息的列表。
     * @author CAI
     * @since 2024-02-26
     */
    @GetMapping("workplace/dynamic")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getDynamic() {
        ResponseEntity<ApiResponse<List<Map<String, Object>>>> response = workplaceService.getDynamic();

        return ResponseEntity.ok(response.getBody());
    }

    /**
     * 获取团队信息的接口。
     * 调用workplaceService的getTeam方法获取团队信息，并返回给前端。
     *
     * @return 团队信息的响应实体，包含多个团队的列表信息。
     * @author CAI
     * @since 2024-02-26
     */
    @GetMapping("workplace/team")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getTeam() {
        ResponseEntity<ApiResponse<List<Map<String, Object>>>> response = workplaceService.getTeam();

        return ResponseEntity.ok(response.getBody());
    }

    /**
     * 获取指数数据的接口。
     * 调用workplaceService的getRadar方法获取指数数据，并返回给前端。
     *
     * @return 指数数据的响应实体，包含多个指数数据的列表信息。
     * @author CAI
     * @since 2024-02-26
     */
    @GetMapping("workplace/radar")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getRadar() {
        ResponseEntity<ApiResponse<List<Map<String, Object>>>> response = workplaceService.getReadar();

        return ResponseEntity.ok(response.getBody());
    }
}
