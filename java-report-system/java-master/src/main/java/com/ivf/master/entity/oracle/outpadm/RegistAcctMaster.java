package com.ivf.master.entity.oracle.outpadm;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Date;

/**
 * 挂号结帐主记录实体类，用于映射数据库表 OUTPADM.REGIST_ACCT_MASTER
 */
@Data
@TableName("OUTPADM.REGIST_ACCT_MASTER")  // 映射 OUTPADM.REGIST_ACCT_MASTER 表
public class RegistAcctMaster {

    @Column(name = "ACCT_NO")  // 映射 OUTPADM.REGIST_ACCT_MASTER 表中的 ACCT_NO 字段
    private String acctNo;  // 结帐序号

    @Column(name = "ACCT_DATE")  // 映射 OUTPADM.REGIST_ACCT_MASTER 表中的 ACCT_DATE 字段
    private Date acctDate;  // 结帐日期

    @Column(name = "OPERATOR_NO")  // 映射 OUTPADM.REGIST_ACCT_MASTER 表中的 OPERATOR_NO 字段
    private String operatorNo;  // 收款员号(staff_dict.id)

    @Column(name = "REGIST_NUM")  // 映射 OUTPADM.REGIST_ACCT_MASTER 表中的 REGIST_NUM 字段
    private int registNum;  // 挂号人次

    @Column(name = "REFUND_NUM")  // 映射 OUTPADM.REGIST_ACCT_MASTER 表中的 REFUND_NUM 字段
    private int refundNum;  // 退费人次

    @Column(name = "REFUND_AMOUNT")  // 映射 OUTPADM.REGIST_ACCT_MASTER 表中的 REFUND_AMOUNT 字段
    private double refundAmount;  // 退费金额

    @Column(name = "TOTAL_COSTS")  // 映射 OUTPADM.REGIST_ACCT_MASTER 表中的 TOTAL_COSTS 字段
    private double totalCosts;  // 总费用

    @Column(name = "TOTAL_INCOMES")  // 映射 OUTPADM.REGIST_ACCT_MASTER 表中的 TOTAL_INCOMES 字段
    private double totalIncomes;  // 总收入

    @Column(name = "TALLY_DATE")  // 映射 OUTPADM.REGIST_ACCT_MASTER 表中的 TALLY_DATE 字段
    private Date tallyDate;  // 记帐日期
}
