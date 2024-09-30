package com.ivf.master.entity.oracle.medrec;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

/**
 * 病人住院主记录实体类，用于映射数据库表 MEDREC.PAT_VISIT
 */
@Data
@TableName("MEDREC.PAT_VISIT")  // 映射 MEDREC.PAT_VISIT 表
public class PatVisit {

    @Column(name = "PATIENT_ID")  // 映射 MEDREC.PAT_VISIT 表中的 PATIENT_ID 字段
    private String patientId;  // 病人标识号

    @Column(name = "VISIT_ID")  // 映射 MEDREC.PAT_VISIT 表中的 VISIT_ID 字段
    private String visitId;  // 病人本次住院标识

    @Column(name = "ADMISSION_DATE_TIME")  // 映射 MEDREC.PAT_VISIT 表中的 ADMISSION_DATE_TIME 字段
    private Date admissionDateTime;  // 入院日期及时间

    @Column(name = "DEPT_ADMISSION_TO")  // 映射 MEDREC.PAT_VISIT 表中的 DEPT_ADMISSION_TO 字段
    private Date deptAdmissionTo;  // 入院科室

    @Column(name = "DISCHARGE_DATE_TIME")  // 映射 MEDREC.PAT_VISIT 表中的 DISCHARGE_DATE_TIME 字段
    private Date dischargeDateTime;  // 出院日期及时间

    @Column(name = "DEPT_DISCHARGE_FROM")  // 映射 MEDREC.PAT_VISIT 表中的 DEPT_DISCHARGE_FROM 字段
    private Date deptDischargeFrom;  // 出院科室
}
