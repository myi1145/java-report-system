package com.ivf.master.entity.oracle.outpadm;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Date;

/**
 * 挂号结帐明细记录实体类，用于映射数据库表 OUTPADM.REGIST_ACCT_DETAIL
 */
@Data
@TableName("OUTPADM.REGIST_ACCT_DETAIL")  // 映射 OUTPADM.REGIST_ACCT_DETAIL 表
public class RegistAcctDetail {

    @Column(name = "ACCT_NO")  // 映射 OUTPADM.REGIST_ACCT_DETAIL 表中的 ACCT_NO 字段
    private String acctNo;  // 结帐序号

    @Column(name = "TALLY_FEE_CLASS")  // 映射 OUTPADM.REGIST_ACCT_DETAIL 表中的 TALLY_FEE_CLASS 字段
    private String tallyFeeClass;  // 费用帐目分类

    private double costs;  // 费用

    private double income;  // 收入
}
