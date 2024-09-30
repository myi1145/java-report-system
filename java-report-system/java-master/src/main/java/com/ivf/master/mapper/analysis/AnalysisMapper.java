package com.ivf.master.mapper.analysis;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 分析页的MyBatis Mapper接口
 * 用于分析页视图的数据查询
 */
@Mapper
public interface AnalysisMapper {

    /**
     * 今日就诊患者
     * @return 今日就诊患者数量
     * @author CAI
     * @since 2024-02-27
     */
    int getVisitsCount();

    /**
     * 今日发生总额
     * @return 今日发生总额
     * @author CAI
     * @since 2024-02-28
     */
    double getTodayTotal();

    /**
     * 今日住院病人总数
     * @return 今日住院病人总数
     * @author CAI
     * @since 2024-02-28
     */
    int getInHospital();

    /**
     * 当日取卵移植总人数
     * @return 当日取卵移植总人数
     * @author CAI
     * @since 2024-04-23
     */
    int getTransplant();

    /**
     * 今日入院病人总数
     * @return 今日入院病人总数
     * @author CAI
     * @since 2024-04-24
     */
    int getBeHospital();

    /**
     * 今日出院病人总数
     * @return 今日出院病人总数
     * @author CAI
     * @since 2024-04-24
     */
    int getOutHospital();

    /**
     * 当日取卵移植总人数
     * @return 当日取卵移植总人数
     * @author CAI
     * @since 2024-04-23
     */
    Map<String, Object> getTransplantClinic();

    /**
     * 今日门诊收入构成
     * @return 今日门诊收入构成
     * @author CAI
     * @since 2024-03-01
     */
    List<Map<String, Object>> getRateTypeListMap();

    /**
     * 近七天就诊量
     * @return 近七天就诊量和日期
     * @author CAI
     * @since 2024-04-19
     */
    List<Map<String, Object>> getWeeklyVisitsListMap();

    /**
     * 获取用户的院区
     * 根据用户名，在部门表获取对应的院区代码
     * @author CAI
     * @since 2024-03-15
     */
    String getHospitalCode(String username);

    /**
     * 本月和上月交易金额对比
     * @return 本月和上月交易金额
     * @author CAI
     * @since 2024-04-21
     */
    List<Map<String, Object>> getMonthlyMoneyListMap();
}
