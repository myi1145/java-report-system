package com.ivf.master.entity.oracle.inpadm;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

/**
 * 在院病人记录实体类，用于映射数据库表 INPADM.PATS_IN_HOSPITAL
 */
@Data
@TableName("INPADM.PATS_IN_HOSPITAL")  // 映射 INPADM.PATS_IN_HOSPITAL 表
public class PatsInHospital {

    @Column(name = "PATIENT_ID")  // 映射 INPADM.PATS_IN_HOSPITAL 表中的 PATIENT_ID 字段
    private String patientId;  // 病人标识号

    @Column(name = "VISIT_ID")  // 映射 INPADM.PATS_IN_HOSPITAL 表中的 VISIT_ID 字段
    private String visitId;  // 病人本次住院标识

    @Column(name = "WARD_CODE")  // 映射 INPADM.PATS_IN_HOSPITAL 表中的 WARD_CODE 字段
    private String wardCode;  // 所在病房代码（护理行为护理单元）

    @Column(name = "DEPT_CODE")  // 映射 INPADM.PATS_IN_HOSPITAL 表中的 DEPT_CODE 字段
    private String deptCode;  // 所在科室代码（护理行为科室）

    @Column(name = "BED_NO")  // 映射 INPADM.PATS_IN_HOSPITAL 表中的 BED_NO 字段
    private String bedNo;  // 床号

    @Column(name = "ADMISSION_DATE_TIME")  // 映射 INPADM.PATS_IN_HOSPITAL 表中的 ADMISSION_DATE_TIME 字段
    private String admissionDateTime;  // 入院日期及时间
}
