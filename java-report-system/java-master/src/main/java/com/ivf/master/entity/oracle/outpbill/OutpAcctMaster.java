package com.ivf.master.entity.oracle.outpbill;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

/**
 * 门诊收费结帐主记录实体类，用于映射数据库表 OUTPBILL.OUTP_ACCT_MASTER
 */
@Data
@TableName("OUTPBILL.OUTP_ACCT_MASTER")  // 映射 OUTPBILL.OUTP_ACCT_MASTER 表
public class OutpAcctMaster {

    @Column(name = "ACCT_NO")  // 映射 OUTPBILL.OUTP_ACCT_MASTER 表中的 ACCT_NO 字段
    private String acctNo;  // 结帐序号

    @Column(name = "OPERATOR_NO")  // 映射 OUTPBILL.OUTP_ACCT_MASTER 表中的 OPERATOR_NO 字段
    private String operatorNo;  // 收款员号(staff_dict.id)

    @Column(name = "OPERATOR_NAME")  // 映射 OUTPBILL.OUTP_ACCT_MASTER 表中的 OPERATOR_NAME 字段
    private String operatorName;  // 收款员姓名

    @Column(name = "ACCT_DATE")  // 映射 OUTPBILL.OUTP_ACCT_MASTER 表中的 ACCT_DATE 字段
    private Date acctDate;  // 结帐日期
}
