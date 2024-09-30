package com.ivf.master.entity.oracle.outpbill;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

/**
 * 门诊医疗收据记录实体类，用于映射数据库表 OUTPBILL.OUTP_RCPT_MASTER
 */
@Data
@TableName("OUTPBILL.OUTP_RCPT_MASTER")  // 映射 OUTPBILL.OUTP_RCPT_MASTER 表
public class OutpRcptMaster {

    @Column(name = "RCPT_NO")  // 映射 OUTPBILL.OUTP_RCPT_MASTER 表中的 RCPT_NO 字段
    private String rcptNo;  // 收据号

    @Column(name = "PATIENT_ID")  // 映射 OUTPBILL.OUTP_RCPT_MASTER 表中的 PATIENT_ID 字段
    private String patientId;  // 病人索引号

    private String name;  // 病人姓名

    @Column(name = "VISIT_DATE")  // 映射 OUTPBILL.OUTP_RCPT_MASTER 表中的 VISIT_DATE 字段
    private Date visitDate;  //  就诊日期

    @Column(name = "TOTAL_COSTS")  // 映射 OUTPBILL.OUTP_RCPT_MASTER 表中的 TOTAL_COSTS 字段
    private double totalCosts;  // 总费用

    @Column(name = "CHARGE_INDICATOR")  // 映射 OUTPBILL.OUTP_RCPT_MASTER 表中的 CHARGE_INDICATOR 字段
    private int chargeIndicator;  // 交费标志(0-正常交费1-欠费 2-已退费)

    @Column(name = "REFUNDED_RCPT_NO")  // 映射 OUTPBILL.OUTP_RCPT_MASTER 表中的 REFUNDED_RCPT_NO 字段
    private String refundedRcptNo;  // 退费收据号

    @Column(name = "ACCT_NO")  // 映射 OUTPBILL.OUTP_RCPT_MASTER 表中的 ACCT_NO 字段
    private String acctNo;  // 结帐序号
}
