package com.ivf.master.entity.oracle.inpbill;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Date;

/**
 * 住院病人结算主记录实体类，用于映射数据库表 INPBILL.INP_SETTLE_MASTER
 */
@Data
@TableName("INPBILL.INP_SETTLE_MASTER")  // 映射 INPBILL.INP_SETTLE_MASTER 表
public class InpSettleMaster {

    @Column(name = "RCPT_NO")  // 映射 INPBILL.INP_SETTLE_MASTER 表中的 RCPT_NO 字段
    private String rcptNo;  // 收据号

    @Column(name = "PATIENT_ID")  // 映射 INPBILL.INP_SETTLE_MASTER 表中的 PATIENT_ID 字段
    private String patientId;  // 病人索引号

    @Column(name = "VISIT_ID")  // 映射 INPBILL.INP_SETTLE_MASTER 表中的 VISIT_ID 字段
    private int visitId;  // 病人本次住院标识

    @Column(name = "SETTLING_DATE")  // 映射 INPBILL.INP_SETTLE_MASTER 表中的 SETTLING_DATE 字段
    private Date settlingDate;  // 结算日期

    private double costs;  // 总费用

    @Column(name = "TRANSACT_TYPE")  // 映射 INPBILL.INP_SETTLE_MASTER 表中的 TRANSACT_TYPE 字段
    private String transactType;  // 结算操作类型

    @Column(name = "REFUNDED_RCPT_NO")  // 映射 INPBILL.INP_SETTLE_MASTER 表中的 REFUNDED_RCPT_NO 字段
    private String refundedRcptNo;  // 退费收据号
}
