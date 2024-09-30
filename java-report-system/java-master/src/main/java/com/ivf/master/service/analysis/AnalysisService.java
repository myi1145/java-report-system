package com.ivf.master.service.analysis;

import com.ivf.master.common.DataSourceContextHolder;
import com.ivf.master.mapper.analysis.AnalysisMapper;
import com.ivf.master.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 提供数据分析相关的服务。
 * 该服务类负责处理与数据分析相关的逻辑，包括获取用户访问来源、用户活跃度以及销售额等信息。
 *
 * @author CAI
 * @since 2024-02-26
 */
@Service
public class AnalysisService {

    private final AnalysisMapper analysisMapper;

    @Autowired
    public AnalysisService(AnalysisMapper analysisMapper) {
        this.analysisMapper = analysisMapper;
    }

    String[] dataSourceList = {};

    /**
     * 获取综合分析数据。
     * 该方法汇总并返回一系列综合数据，包括今日就诊量、消息总数、今日发生金额及今日在院病人总数情况。
     *
     * @return 包含综合分析数据的ResponseEntity对象。
     * @author CAI
     * @since 2024-02-26
     */
    public ResponseEntity<ApiResponse<Map<String, Object>>> getTotal(String username) {

        setUsername(username);

        Map<String, Object> totalMap = new LinkedHashMap<>();

        // 今日就诊量  CAI 2024-02-27
        int visitsSum = getVisitsSum(dataSourceList);

        // 今日发生金额  CAI 2024-02-28
        double todayTotals = getTodayTotals(dataSourceList);

        // 今日在院病人总数 CAI 2024-02-28
        int inHospSum = getInHospSum(dataSourceList);

        // 今日取卵移植 CAI 2024-04-23
        int transplantSum = getTransplantSum(dataSourceList);

        totalMap.put("visitsSum", visitsSum);
        totalMap.put("transplantSum", transplantSum);
        totalMap.put("moneys", todayTotals);
        totalMap.put("inHospSum", inHospSum);

        ApiResponse<Map<String, Object>> response = new ApiResponse<>(
                ApiResponse.SUCCESS,
                "getTotal",
                totalMap
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 获取当日各科室取卵人数
     *
     * @return 当日各科室取卵人数
     * @author CAI
     * @since 2024-04-24
     */
    public ResponseEntity<ApiResponse<Map<String, Object>>> getTransplantClinic(String username) {
        setUsername(username);

        Map<String, Object> transplantClinicMap = getTransplantClinic(dataSourceList);

        ApiResponse<Map<String, Object>> response = new ApiResponse<>(
                ApiResponse.SUCCESS,
                "getTransplantClinic",
                transplantClinicMap
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 获取当日各科室取卵人数
     *
     * @return 当日各科室取卵人数
     * @author CAI
     * @since 2024-04-24
     */
    public ResponseEntity<ApiResponse<Map<String, Object>>> getInHospital(String username) {
        setUsername(username);

        Map<String, Object> inHospitalMap = new LinkedHashMap<>();

        // 今日入院病人总数  CAI 2024-04-24
        int beHospSum = getBeHospSum(dataSourceList);

        // 今日在院病人总数 CAI 2024-04-24
        int inHospSum = getInHospSum(dataSourceList);

        // 今日出院病人总数  CAI 2024-04-24
        int outHospSum = getOutHospSum(dataSourceList);

        inHospitalMap.put("beHosp", beHospSum);
        inHospitalMap.put("inHosp", inHospSum);
        inHospitalMap.put("outHosp", outHospSum);

        ApiResponse<Map<String, Object>> response = new ApiResponse<>(
                ApiResponse.SUCCESS,
                "inHospitalMap",
                inHospitalMap
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 获取当日门诊收费明细分类。
     * 分析并返回门诊收费明细分类，如化验、检查、西药等。
     *
     * @return 包含当日门诊收费明细分类数据的ResponseEntity对象。
     * @author CAI
     * @since 2024-03-01
     */
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getRateType(String username) {

        setUsername(username);
        // 当日门诊收入构成
        List<Map<String, Object>> rateType = getRateType(dataSourceList);

        ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>(
                ApiResponse.SUCCESS,
                "getRateTypeList",
                rateType
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 获取每周用户活跃量数据。
     * 分析并返回一周中每天的用户活跃量，以便了解用户活跃度的变化趋势。
     *
     * @return 包含每周用户活跃量数据的ResponseEntity对象。
     * @author CAI
     * @since 2024-02-26
     */
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getWeeklyUserActivity(String username) {

        setUsername(username);

        List<Map<String, Object>> weeklyUserActivityListMap = getWeeklyVisits(dataSourceList);

        ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>(
                ApiResponse.SUCCESS,
                "getWeeklyUserActivity",
                weeklyUserActivityListMap
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 获取每月销售额数据。
     * 分析并返回每月的预估销售额与实际销售额，帮助了解销售趋势和业务表现。
     *
     * @return 包含每月销售额数据的ResponseEntity对象。
     * @author CAI
     * @since 2024-02-26
     */
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getMonthlySales(String username) {
        setUsername(username);

        List<Map<String, Object>> monthlyOutpMoneyListMap = getMonthlyOutpMoneyListMap(dataSourceList);

        ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>(
                ApiResponse.SUCCESS,
                "getWeeklyUserActivity",
                monthlyOutpMoneyListMap
        );

        return ResponseEntity.ok(response);
    }


    /**
     * 获取四家医院的当日就诊患者
     *
     * @param dataSourceList 数据源
     * @return visitsSum 四家医院当日就诊患者的总数
     * @author CAI
     * @since 2024-02-27
     */
    public int getVisitsSum(String[] dataSourceList) {
        int visitsSum = 0;

        for (String s : dataSourceList) {
            DataSourceContextHolder.setDataSource(s);

            int visitsCount = analysisMapper.getVisitsCount();
            visitsSum += visitsCount;
        }


        return visitsSum;
    }

    /**
     * 获取四家医院的今日门诊实收金额总数
     *
     * @param dataSourceList 数据源
     * @return todayTotals 四家医院的今日门诊实收金额总数
     * @author CAI
     * @since 2024-02-28
     */
    public double getTodayTotals(String[] dataSourceList) {
        double todayTotals = 0;

        for (String s : dataSourceList) {
            DataSourceContextHolder.setDataSource(s);

            double todayTotal = analysisMapper.getTodayTotal();
            todayTotals += todayTotal;
        }

        String str = String.format("%.2f", todayTotals);

        return Double.parseDouble(str);
    }

    /**
     * 获取四家医院的当日住院总人数
     *
     * @param dataSourceList 数据源
     * @return inHospSum 当日住院总人次
     * @author CAI
     * @since 2024-02-28
     */
    public int getInHospSum(String[] dataSourceList) {
        int inHospSum = 0;

        for (String s : dataSourceList) {
            DataSourceContextHolder.setDataSource(s);

            int inHospital = analysisMapper.getInHospital();
            inHospSum += inHospital;
        }

        return inHospSum;
    }

    /**
     * 获取四家医院的当日入院总人数
     *
     * @param dataSourceList 数据源
     * @return inHospSum 当日入院总人数
     * @author CAI
     * @since 2024-04-24
     */
    public int getBeHospSum(String[] dataSourceList) {
        int beHospSum = 0;

        for (String s : dataSourceList) {
            DataSourceContextHolder.setDataSource(s);

            int beHospital = analysisMapper.getBeHospital();
            beHospSum += beHospital;
        }

        return beHospSum;
    }

    /**
     * 获取四家医院的当日出院总人数
     *
     * @param dataSourceList 数据源
     * @return inHospSum 当日出院总人数
     * @author CAI
     * @since 2024-04-24
     */
    public int getOutHospSum(String[] dataSourceList) {
        int outHospSum = 0;

        for (String s : dataSourceList) {
            DataSourceContextHolder.setDataSource(s);

            int outHospital = analysisMapper.getOutHospital();
            outHospSum += outHospital;
        }

        return outHospSum;
    }

    /**
     * 获取四家医院的当日取卵移植总人数
     *
     * @param dataSourceList 数据源
     * @return transplantSum 当日取卵移植总人数
     * @author CAI
     * @since 2024-04-23
     */
    public int getTransplantSum(String[] dataSourceList) {
        int transplantSum = 0;

        for (String s : dataSourceList) {
            if (s.equals("kmhis")) {
                DataSourceContextHolder.setDataSource("kmhc");

                int transplant = analysisMapper.getTransplant();
                transplantSum += transplant;
            }
        }

        return transplantSum;
    }

    /**
     * 获取四家医院的当日门诊收入构成
     *
     * @param dataSourceList 数据源
     * @return dataList 当日门诊收入构成
     * @author CAI
     * @sinse 2024-03-01
     */
    public List<Map<String, Object>> getRateType(String[] dataSourceList) {
        Map<String, BigDecimal> listMap = new HashMap<>();

        for (String s : dataSourceList) {
            DataSourceContextHolder.setDataSource(s);

            List<Map<String, Object>> rateTypeMap = analysisMapper.getRateTypeListMap();

            for (Map<String, Object> result : rateTypeMap) {
                String itemClass = (String) result.get("SUBJ_NAME");
                BigDecimal costs = new BigDecimal(result.get("COSTS").toString()).setScale(2, RoundingMode.HALF_UP);
                listMap.put(itemClass, listMap.getOrDefault(itemClass, BigDecimal.ZERO).add(costs));
            }
        }

        List<Map.Entry<String, BigDecimal>> list = new ArrayList<>(listMap.entrySet());

        // 根据费用降序排序
        list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // 获取费用最高的前七位
        List<Map.Entry<String, BigDecimal>> toSeven = list.subList(0, Math.min(7, list.size()));

        ArrayList<Map<String, Object>> dataList = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> entry : toSeven) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", entry.getKey());
            data.put("value", entry.getValue().setScale(2, RoundingMode.HALF_UP));
            dataList.add(data);
        }

        return dataList;
    }

    /**
     * 获取近七天的每日就诊量
     *
     * @author CAI
     * @sinse 2024-03-19
     */
    public List<Map<String, Object>> getWeeklyVisits(String[] dataSourceList) {
        Map<String, Integer> listMap = new HashMap<>();

        for (String s : dataSourceList) {
            DataSourceContextHolder.setDataSource(s);

            List<Map<String, Object>> weeklyVisits = analysisMapper.getWeeklyVisitsListMap();

            for (Map<String, Object> result : weeklyVisits) {
                String visitDate = (String) result.get("VISIT_DATE");
                Integer count = Integer.valueOf(result.get("COUNT").toString());
                listMap.put(visitDate, listMap.getOrDefault(visitDate, 0) + count);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(listMap.entrySet());

        ArrayList<Map<String, Object>> dataList = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : list) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", entry.getKey());
            data.put("value", entry.getValue());
            dataList.add(data);
        }


        return dataList;
    }

    /**
     * 本月和上月总门诊收入对比
     *
     * @author CAI
     * @since 2024-04-21
     */
    public List<Map<String, Object>> getMonthlyOutpMoneyListMap(String[] dataSourceList) {
        Map<String, Map<String, BigDecimal>> listMap = new HashMap<>();

        for (String s : dataSourceList) {
            DataSourceContextHolder.setDataSource(s);

            List<Map<String, Object>> monthlyMoneyMap = analysisMapper.getMonthlyMoneyListMap();

            for (Map<String, Object> result : monthlyMoneyMap) {

                try {
                    String day = (String) result.get("DAY");

                    BigDecimal leftSum = new BigDecimal(result.get("LEFT_SUM").toString()).setScale(2, RoundingMode.HALF_UP);
                    BigDecimal thisSum = new BigDecimal(result.get("THIS_SUM").toString()).setScale(2, RoundingMode.HALF_UP);

                    listMap.computeIfAbsent(day, k -> new HashMap<>());

                    listMap.get(day).put("LEFT_SUM", listMap.get(day).getOrDefault("LEFT_SUM", BigDecimal.ZERO).add(leftSum));
                    listMap.get(day).put("THIS_SUM", listMap.get(day).getOrDefault("THIS_SUM", BigDecimal.ZERO).add(thisSum));

                } catch (NullPointerException | NumberFormatException e) {
                    System.err.println("Error processing data: " + e.getMessage());
                }
            }

        }

        List<Map.Entry<String, Map<String, BigDecimal>>> sortedList = new ArrayList<>(listMap.entrySet());
        sortedList.sort(Comparator.comparing(Map.Entry::getKey));

        List<Map<String, Object>> dataList = sortedList.stream()
                .map(entry -> {
                    Map<String, Object> data = new HashMap<>();
                    data.put("day", entry.getKey());
                    data.put("leftSum", entry.getValue().get("LEFT_SUM"));
                    data.put("thisSum", entry.getValue().get("THIS_SUM"));
                    return data;
                })
                .collect(Collectors.toList());


        return dataList;

    }

    public Map<String, Object> getTransplantClinic(String[] dataSourceList) {

        Map<String, Object> clinic = null;

        for (String s : dataSourceList) {
            DataSourceContextHolder.setDataSource(s);

            if (s.equals("kmhis")) {
                DataSourceContextHolder.setDataSource("kmhc");

                clinic = analysisMapper.getTransplantClinic();
                System.out.println(clinic);
            }

        }

        return clinic;
    }

    public Map<String, Object> getInHospitalCount(String[] dataSourceList) {

        Map<String, Object> inHospital = null;

        for (String s : dataSourceList) {
            DataSourceContextHolder.setDataSource(s);

            inHospital = analysisMapper.getTransplantClinic();
            System.out.println(inHospital);
        }

        return inHospital;
    }


    /**
     * 添加对数据源的判断和添加，如果是医院的，则为单个数据源，如果是集团的则是所有数据源
     *
     * @author CAI
     * @since 2024-03-15
     */
    public void setUsername(String username) {
        // 设置当前线程的数据源为MySQL
        DataSourceContextHolder.setDataSource("webmysql");

        String hospitalCode = analysisMapper.getHospitalCode(username);

        switch (hospitalCode) {
            case "IVF":
                dataSourceList = new String[]{"jyhis", "kmhis", "tjivf", "zjhis"};
                break;
            case "JYH":
                dataSourceList = new String[]{"jyhis"};
                break;
            case "KMH":
                dataSourceList = new String[]{"kmhis"};
                break;
            case "TJH":
                dataSourceList = new String[]{"tjivf"};
                break;
            case "ZJH":
                dataSourceList = new String[]{"zjhis"};
                break;
        }
    }
}
