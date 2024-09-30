package com.ivf.master.service.maintain;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ivf.master.common.DataSourceContextHolder;
import com.ivf.master.dto.AccountRequest;
import com.ivf.master.dto.AccountTitleRequest;
import com.ivf.master.entity.oracle.outpbill.OutpAccContrast;
import com.ivf.master.entity.oracle.outpbill.OutpBillItems;
import com.ivf.master.mapper.oracle.outpbill.OutpAccContrastMapper;
import com.ivf.master.mapper.oracle.outpbill.OutpBillItemsMapper;
import com.ivf.master.utils.ApiResponse;
import com.ivf.master.utils.GeneralUtils;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service
public class MaintainService {

    private final OutpBillItemsMapper outpBillItemsMapper;
    private final OutpAccContrastMapper outpAccContrastMapper;

    @Autowired
    public MaintainService(OutpBillItemsMapper outpBillItemsMapper, OutpAccContrastMapper outpAccContrastMapper) {
        this.outpBillItemsMapper = outpBillItemsMapper;
        this.outpAccContrastMapper = outpAccContrastMapper;
    }

    public ResponseEntity<ApiResponse<IPage<AccountTitleRequest>>> getAccountTitle(AccountRequest accountRequest) {

        // 设置当前线程的数据源为 KMHIS
        DataSourceContextHolder.setDataSource("kmhis");

        Map<String, Object> hashMap = new HashMap<>();

        ApiResponse<IPage<AccountTitleRequest>> response;

        // 判断是否存在其它查询条件
        String itemCode = accountRequest.getItemCode();
        String itemName = accountRequest.getItemName();
        Date startDate = accountRequest.getStartDate();
        Date endDate = accountRequest.getEndDate();

        if (startDate == null) startDate = GeneralUtils.LocalDToDate(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth())); // 获取当前月的第一天
        if (endDate == null) endDate = GeneralUtils.LocalDToDate(LocalDate.now()); // 今天

        // 判断如果查询条件为空，则为 %
        if (itemCode == null || itemCode.trim().isEmpty()) itemCode = "%";
        else itemCode = "%" + itemCode + "%";
        if (itemName == null || itemName.trim().isEmpty()) itemName = "%";
        else itemName = "%" + itemName + "%";

        // 查询库中是否存在数据
        IPage<AccountTitleRequest> accountLists = outpBillItemsMapper.getAccountTitle(
                new Page<>(accountRequest.getPageIndex(), accountRequest.getPageSize()),
                itemCode, itemName, startDate, endDate);

        System.out.println(accountLists);

//        if (GeneralUtils.isListEmpty((List<?>) accountLists)) {
//
//            response = new ApiResponse<>(
//                    ApiResponse.NOT_FOUND,
//                    "该时间段无尚未维护的会计科目对照信息！",
//                    null
//            );
//
//            return ResponseEntity.ok(response);
//        }
//
//        hashMap.put("list", accountLists);
//        hashMap.put("total", accountLists.getTotal());

        response = new ApiResponse<>(
                ApiResponse.SUCCESS,
                "SUCCESS",
                accountLists
        );

        return ResponseEntity.ok(response);

    }

    /**
     * 插入会计科目对照表数据
     *
     * @param data 插入数据
     * @return 返回成功状态
     */
    public ResponseEntity<ApiResponse<String>> saveAccountData(Map<String, Object> data) {

        // 设置当前线程的数据源为 KMHIS
        DataSourceContextHolder.setDataSource("kmhis");

        ApiResponse<String> response;

        Map<String, Object> stringData = GeneralUtils.processMap(data);

        System.out.println(stringData);
        String itemCode = stringData.get("itemCode").toString();
        String yyAccCode = stringData.get("yyAccCode").toString();

        // 判断项目代码是否为空
        if (StringUtils.isNullOrEmpty(itemCode)) {
            response = new ApiResponse<>(
                    ApiResponse.FORBIDDEN,
                    "项目代码为空！",
                    null
            );

            return ResponseEntity.ok(response);
        }

        if (StringUtils.isNullOrEmpty(yyAccCode)) {
            response = new ApiResponse<>(
                    ApiResponse.FORBIDDEN,
                    "用友会计科目代码为空！",
                    null
            );

            return ResponseEntity.ok(response);
        }

        // 获取当前时间段未维护的会计科目代码
        LambdaQueryWrapper<OutpAccContrast> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OutpAccContrast::getItemCode, itemCode);

        // 查询库中是否存在数据
        OutpAccContrast selectOne = outpAccContrastMapper.selectOne(queryWrapper);

        try {
            OutpAccContrast outpAccContrast = GeneralUtils.mapToObj(stringData, OutpAccContrast.class);

            if (Objects.isNull(selectOne)) {
                System.out.println("插入");
                outpAccContrastMapper.insert(outpAccContrast);
            } else {
                System.out.println("更新");
                outpAccContrastMapper.update(outpAccContrast, queryWrapper);
            }

            System.out.println(outpAccContrast);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        response = new ApiResponse<>(
                ApiResponse.SUCCESS,
                "保存成功！",
                null
        );

        return ResponseEntity.ok(response);
    }
}
