package com.ivf.master.entity.oracle.comm;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

/**
 * 门诊收据费用分类字典实体类，用于映射数据库表 COMM.OUTP_RCPT_FEE_DICT
 */
@Data
@TableName("COMM.OUTP_RCPT_FEE_DICT")  // 映射 COMM.OUTP_RCPT_FEE_DICT 表
public class OutpRcptFeeDict {

    @Column(name = "SERIAL_NO")  // 映射 COMM.OUTP_RCPT_FEE_DICT 表中的 SERIAL_NO 字段
    private String serialNo;  //  序号

    @Column(name = "FEE_CLASS_CODE")  // 映射 COMM.BILL_ITEM_CLASS_DICT 表中的 FEE_CLASS_CODE 字段
    private String feeClassCode;  //  费用分类代码

    @Column(name = "FEE_CLASS_NAME")  // 映射 COMM.BILL_ITEM_CLASS_DICT 表中的 FEE_CLASS_NAME 字段
    private String feeClassName;  //  费用分类名称
}
