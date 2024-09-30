package com.ivf.master.dto;

import lombok.Data;

@Data
public class AccountTitleRequest {

    private String itemCode;

    private String itemName;

    private String classOnReckoning;

    private String className;

    private String tjAccCode;

    private String tjAccName;

    private String yyAccCode;

    private String yyAccName;
}
