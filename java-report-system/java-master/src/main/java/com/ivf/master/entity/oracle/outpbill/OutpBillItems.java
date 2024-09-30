package com.ivf.master.entity.oracle.outpbill;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

/**
 * 门诊病人收费费用项目实体类，用于映射数据库表 OUTPBILL.OUTP_BILL_ITEMS
 */
@Data
@TableName("OUTPBILL.OUTP_BILL_ITEMS")  // 映射 OUTPBILL.OUTP_BILL_ITEMS 表
public class OutpBillItems {

    @Column(name = "VISIT_DATE")  // 映射 OUTPBILL.OUTP_BILL_ITEMS 表中的 VISIT_DATE 字段
    private Date visitDate;  //  就诊日期

    @Column(name = "RCPT_NO")  // 映射 OUTPBILL.OUTP_BILL_ITEMS 表中的 RCPT_NO 字段
    private String rcptNo;  // 收据号

    @Column(name = "VISIT_NO")  // 映射 OUTPBILL.OUTP_BILL_ITEMS 表中的 VISIT_NO 字段
    private int visitNo;  // 就诊序号

    @Column(name = "ITEM_NO")  // 映射 OUTPBILL.OUTP_BILL_ITEMS 表中的 ITEM_NO 字段
    private int itemNo;  // 序号

    @Column(name = "ITEM_CLASS")  // 映射 OUTPBILL.OUTP_BILL_ITEMS 表中的 ITEM_CLASS 字段
    private String itemClass;  // 价表项目分类

    @Column(name = "ITEM_CODE")  // 映射 OUTPBILL.OUTP_BILL_ITEMS 表中的 ITEM_CODE 字段
    private String itemCode;  // 项目代码

    @Column(name = "ITEM_NAME")  // 映射 OUTPBILL.OUTP_BILL_ITEMS 表中的 ITEM_NAME 字段
    private String itemName;  // 项目名称

    @Column(name = "ITEM_SPEC")  // 映射 OUTPBILL.OUTP_BILL_ITEMS 表中的 ITEM_SPEC 字段
    private String itemSpec;  // 项目规格

    private int amount;  // 数量
    private String units;  // 单位
    private double costs;  // 费用
    private double charges;  // 应付费用

    @Column(name = "ORDER_DEPT")  // 映射 OUTPBILL.OUTP_BILL_ITEMS 表中的 ORDER_DEPT 字段
    private String orderDept;  // 开单科室

    @Column(name = "ORDER_DOCTOR")  // 映射 OUTPBILL.OUTP_BILL_ITEMS 表中的 ORDER_DOCTOR 字段
    private String orderDoctor;  // 开单医生(staff_dict.user_name)

    @Column(name = "PERFORMED_BY")  // 映射 OUTPBILL.OUTP_BILL_ITEMS 表中的 PERFORMED_BY 字段
    private String performedBy;  // 执行科室

    @Column(name = "CLASS_ON_RCPT")  // 映射 OUTPBILL.OUTP_BILL_ITEMS 表中的 CLASS_ON_RCPT 字段
    private String classOnRcpt;  // 收据项目分类

}
