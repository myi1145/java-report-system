package com.ivf.master.entity.oracle.system;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Table(name = "SYSTEM.VIEW_MONTY_TYPE_COSTS")
public class MoneyTypeCosts {

    @Column(name = "MONEY_TYPE")
    private  String moneyType;

    @Column(name = "SUM_MZ")
    private  Double sumMz;

    @Column(name = "SUM_MZ_BGYJJ")
    private  Double sumMzBgyjj;

    @Column(name = "SUM_MZ_FBGYJJ")
    private  Double sumMzFbgyjj;

    @Column(name = "SUM_ZY")
    private  Double sumZy;

    @Column(name = "SUM_ZY_YJJ")
    private  Double sumZyYjj;
}
