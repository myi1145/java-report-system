package com.ivf.master.controller.analysis;

import com.ivf.master.service.analysis.AnalysisService;
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
 * 分析控制器，提供数据分析相关的REST API接口。
 * 使用跨域资源共享（CORS）策略允许不同源的请求。
 * 依赖于AnalysisService来获取分析数据。
 *
 * @author CAI
 * @since 2024-02-26
 */
@RestController
@CrossOrigin
public class AnalysisController {

    // 分析服务实例，通过依赖注入自动装配
    private final AnalysisService analysisService;

    /**
     * 构造函数，自动装配分析服务。
     *
     * @param analysisService 分析服务实例
     */
    @Autowired
    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    /**
     * 获取综合分析数据。
     * 返回包含多种分析数据的Map，例如用户访问来源等。
     *
     * @return 包含ApiResponse封装的Map的ResponseEntity，其中包含综合分析数据。
     * @author CAI
     * @since 2024-02-26
     */
    @GetMapping("analysis/total")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getTotal(@RequestParam String username) {
        ResponseEntity<ApiResponse<Map<String, Object>>> response = analysisService.getTotal(username);

        return ResponseEntity.ok(response.getBody());
    }

    /**
     * 当日各科室取卵人数。
     *
     * @return 包含ApiResponse封装的Map的ResponseEntity，当日各科室取卵人数数据。
     * @author CAI
     * @since 2024-04-24
     */
    @GetMapping("analysis/transplant")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getTransplantClinic(@RequestParam String username) {
        ResponseEntity<ApiResponse<Map<String, Object>>> response = analysisService.getTransplantClinic(username);

        return ResponseEntity.ok(response.getBody());
    }

    /**
     * 获取当日门诊收费明细分类。
     * 分析门诊收费明细分类，如化验、检查、西药等。
     *
     * @return 包含ApiResponse封装的List的ResponseEntity，其中包含当日门诊收费明细分类数据。
     * @author CAI
     * @since 2024-03-01
     */
    @GetMapping("analysis/getRateType")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getRateType(@RequestParam String username) {
        ResponseEntity<ApiResponse<List<Map<String, Object>>>> response = analysisService.getRateType(username);

        return ResponseEntity.ok(response.getBody());
    }

    /**
     * 获取每周用户活跃量数据。
     * 分析并返回每周的用户活跃情况，以便了解用户活跃度的变化趋势。
     *
     * @return 包含ApiResponse封装的List的ResponseEntity，其中包含每周用户活跃量数据。
     * @author CAI
     * @since 2024-02-26
     */
    @GetMapping("analysis/weeklyUserActivity")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getWeeklyUserActivity(@RequestParam String username) {
        ResponseEntity<ApiResponse<List<Map<String, Object>>>> response = analysisService.getWeeklyUserActivity(username);

        return ResponseEntity.ok(response.getBody());
    }

    /**
     * 获取每月销售额数据。
     * 分析并返回每月的销售额，帮助了解销售趋势和业务表现。
     *
     * @return 包含ApiResponse封装的List的ResponseEntity，其中包含每月销售额数据。
     * @author CAI
     * @since 2024-02-26
     */
    @GetMapping("analysis/monthlySales")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getMonthlySales(@RequestParam String username) {
        ResponseEntity<ApiResponse<List<Map<String, Object>>>> response = analysisService.getMonthlySales(username);

        return ResponseEntity.ok(response.getBody());
    }

    /**
     * 当日住院人数情况。
     *
     * @return 包含ApiResponse封装的Map的ResponseEntity，当日住院人数情况数据。
     * @author CAI
     * @since 2024-04-24
     */
    @GetMapping("analysis/inHospital")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getInHospital(@RequestParam String username) {
        ResponseEntity<ApiResponse<Map<String, Object>>> response = analysisService.getInHospital(username);

        return ResponseEntity.ok(response.getBody());
    }
}
