package com.ivf.master.entity.oracle.outpadm;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Date;

/**
 * 就诊记录实体类，用于映射数据库表 OUTPADM.CLINIC_MASTER
 */
@Data
@TableName("OUTPADM.CLINIC_MASTER")  // 映射 OUTPADM.CLINIC_MASTER 表
public class ClinicMaster {

    @Column(name = "VISIT_DATE")  // 映射 OUTPADM.CLINIC_MASTER 表中的 VISIT_DATE 字段
    private Date visitDate;  // 就诊日期

    @Column(name = "VISIT_NO")  // 映射 OUTPADM.CLINIC_MASTER 表中的 VISIT_NO 字段
    private int visitNo;  // 就诊序号

    @Column(name = "PATIENT_ID")  // 映射 OUTPADM.CLINIC_MASTER 表中的 PATIENT_ID 字段
    private String patientId;  // 病人ID号

    private String name;  // 姓名

    @Column(name = "CLINIC_CHARGE")  // 映射 OUTPADM.CLINIC_MASTER 表中的 CLINIC_CHARGE 字段
    private double clinicCharge;  // 实收费用

    @Column(name = "RETURNED_DATE")  // 映射 OUTPADM.CLINIC_MASTER 表中的 RETURNED_DATE 字段
    private Date returnedDate;  // 退号日期
}
