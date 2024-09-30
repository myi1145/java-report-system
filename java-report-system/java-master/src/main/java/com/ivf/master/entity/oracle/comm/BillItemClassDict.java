package com.ivf.master.entity.oracle.comm;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

/**
 * 价表项目分类字典实体类，用于映射数据库表 COMM.BILL_ITEM_CLASS_DICT
 */
@Data
@TableName("COMM.BILL_ITEM_CLASS_DICT")  // 映射 COMM.BILL_ITEM_CLASS_DICT 表
public class BillItemClassDict {

    @Column(name = "SERIAL_NO")  // 映射 COMM.BILL_ITEM_CLASS_DICT 表中的 SERIAL_NO 字段
    private String serialNo;  //  序号

    @Column(name = "CLASS_CODE")  // 映射 COMM.BILL_ITEM_CLASS_DICT 表中的 CLASS_CODE 字段
    private String classCode;  // 收据号

    @Column(name = "CLASS_NAME")  // 映射 COMM.BILL_ITEM_CLASS_DICT 表中的 CLASS_NAME 字段
    private String className;  // 收据号
}
