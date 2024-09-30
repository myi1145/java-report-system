package com.ivf.master.entity.oracle.outpbill;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

/**
 * 用于映射数据库表 OUTPBILL.OUTP_ACC_CONTRAST
 */
@Data
@TableName("OUTPBILL.OUTP_ACC_CONTRAST")  // 映射 OUTPBILL.OUTP_ACC_CONTRAST 表
public class OutpAccContrast {

    @Column(name = "ITEM_CODE")  // 项目代码
    private String itemCode;

    @Column(name = "ITEM_NAME")  //
    private String itemName;

    @Column(name = "TJ_ACC_CODE")  //
    private String tjAccCode;

    @Column(name = "TJ_ACC_NAME")  //
    private String tjAccName;

    @Column(name = "YY_ACC_CODE")  //
    private String yyAccCode;

    @Column(name = "YY_ACC_NAME")  //
    private String yyAccName;
}
