package com.ivf.master.dto;

import lombok.Data;

import java.util.Date;


/**
 * 用于会计科目类别查询接收参数
 */
@Data
public class AccountRequest {

    private String itemCode;  // 项目代码

    private String itemName;  // 项目名称

    private Date startDate;  // 开始时间

    private Date endDate;  // 结束时间

    private int pageIndex;  //

    private int pageSize;  //

}
