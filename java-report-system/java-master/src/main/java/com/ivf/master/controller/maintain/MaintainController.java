package com.ivf.master.controller.maintain;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivf.master.dto.AccountRequest;
import com.ivf.master.dto.AccountTitleRequest;
import com.ivf.master.service.maintain.MaintainService;
import com.ivf.master.utils.ApiResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 用于维护各医院的数据入口
 *
 * @author CAI
 * @since 2024年9月17日
 */
@RestController
@CrossOrigin
public class MaintainController {

    private final MaintainService maintainService;

    /**
     * 构造函数，自动装配分析服务。
     *
     * @param maintainService
     */
    @Autowired
    public MaintainController(MaintainService maintainService) {
        this.maintainService = maintainService;
    }

    /**
     * 查询用友会计科目对照表未维护数据
     *
     * @author CAI
     */
    @PostMapping("/mainTain/account/list")
    public ResponseEntity<ApiResponse<IPage<AccountTitleRequest>>> getAccountTitle(@RequestBody Map<String,Object> data) {

        String key = data.get("body").toString();

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<ApiResponse<IPage<AccountTitleRequest>>> response;
        try {
            AccountRequest accountRequest = objectMapper.readValue(key, AccountRequest.class);
            System.out.println("Start Date: " + accountRequest.getStartDate());
            System.out.println("End Date: " + accountRequest.getEndDate());

            response = maintainService.getAccountTitle(accountRequest);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(response.getBody());
    }

    /**
     * 保存尚未在会计科目对照表里面维护的数据
     *
     * @author CAI
     */
    @PostMapping("/mainTain/account/save")
    public ResponseEntity<ApiResponse<String>> saveAccountData(@RequestBody Map<String,Object> data) {

        System.out.println(data);

        ResponseEntity<ApiResponse<String>> response;

        response = maintainService.saveAccountData(data);

        return ResponseEntity.ok(response.getBody());
    }

}
