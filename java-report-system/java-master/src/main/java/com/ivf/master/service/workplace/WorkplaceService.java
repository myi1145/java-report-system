package com.ivf.master.service.workplace;

import com.ivf.master.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * workplace服务类，负责提供workplace页面所需的各种数据。
 * 该服务类提供的数据通常用于前端展示workplace的统计信息、项目信息、动态、团队信息以及指数等。
 *
 * @author CAI
 * @since 2024-02-26
 */
@Service
public class WorkplaceService {

    /**
     * 获取workplace的总体统计数据。
     *
     * @return 包含统计数据的响应实体。
     * @author CAI
     * @since 2024-02-26
     */
    public ResponseEntity<ApiResponse<Map<String, Object>>> getTotal() {

        Map<String, Object> totalMap = new LinkedHashMap<>();

        totalMap.put("project", 40);  // 项目总数
        totalMap.put("access", 2340);  // 访问量
        totalMap.put("todo", 10);  // 待办事项数量

        ApiResponse<Map<String, Object>> response = new ApiResponse<>(
                ApiResponse.SUCCESS,
                "getTotal",
                totalMap
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 获取workplace的项目列表信息。
     *
     * @return 包含项目列表信息的响应实体。
     * @author CAI
     * @since 2024-02-26
     */
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getProject() {

        List<Map<String, Object>> projectListMap = new ArrayList<>();

        // 示例数据，实际应用中需要从数据库或其他服务获取
        String[] names = {
                "Github", "Vue", "Angular", "React", "Webpack", "Vite",
        };

        String[] icons = {
                "line-md:github-loop",
                "logos:vue",
                "logos:angular-icon",
                "logos:react",
                "logos:webpack",
                "vscode-icons:file-type-vite"
        };

        String message = "workplace.introduction";

        String personal = "Archer";

        Date time = new Date();

        for (int i = 0; i < names.length; i++) {
            Map<String, Object> data = new LinkedHashMap<>();

            data.put("name", names[i]);
            data.put("icon", icons[i]);
            data.put("message", message);
            data.put("personal", personal);
            data.put("time", time);

            projectListMap.add(data);
        }

        ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>(
                ApiResponse.SUCCESS,
                "getProject",
                projectListMap
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 获取workplace的动态信息。
     *
     * @return 包含动态信息的响应实体。
     * @author CAI
     * @since 2024-02-26
     */
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getDynamic() {

        List<Map<String, Object>> dynamicListMap = new ArrayList<>();

        String[] keysList = {"workplace.push", "Github"};
        Date time = new Date();
        int length = 6;

        for (int i = 0; i < length; i++) {
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("keys", keysList);
            data.put("time", time);

            dynamicListMap.add(data);
        }

        ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>(
                ApiResponse.SUCCESS,
                "getProject",
                dynamicListMap
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 获取workplace的团队信息。
     *
     * @return 包含团队信息的响应实体。
     * @author CAI
     * @since 2024-02-26
     */
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getTeam() {

        List<Map<String, Object>> teamListMap = new ArrayList<>();

        String[] names = {"Github", "Vue", "Angular", "React", "Webpack", "Vite"};

        String[] icons = {
                "akar-icons:github-fill",
                "logos:vue",
                "logos:angular-icon",
                "logos:react",
                "logos:webpack",
                "vscode-icons:file-type-vite"

        };

        for (int i = 0; i < names.length; i++) {
            Map<String, Object> data = new LinkedHashMap<>();

            data.put("name", names[i]);
            data.put("icon", icons[i]);

            teamListMap.add(data);
        }

        ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>(
                ApiResponse.SUCCESS,
                "getProject",
                teamListMap
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 获取workplace的指数信息，如雷达图数据。
     *
     * @return 包含指数信息的响应实体。
     * @author CAI
     * @since 2024-02-26
     */
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getReadar() {

        List<Map<String, Object>> readarListMap = new ArrayList<>();

        String[] names = {
                "workplace.quote",
                "workplace.contribution",
                "workplace.hot",
                "workplace.yield",
                "workplace.follow",
        };

        int[] maxs = {65, 160, 300, 130, 100};
        int[] personals = {42, 30, 20, 35, 80};
        int[] teams = {50, 140, 28, 35, 90};

        for (int i = 0; i < names.length; i++) {
            Map<String, Object> data = new LinkedHashMap<>();

            data.put("name", names[i]);
            data.put("max", maxs[i]);
            data.put("personal", personals[i]);
            data.put("team", teams[i]);

            readarListMap.add(data);
        }

        ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>(
                ApiResponse.SUCCESS,
                "getProject",
                readarListMap
        );

        return ResponseEntity.ok(response);
    }
}
