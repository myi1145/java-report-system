package com.ivf.master.mapper.oracle.outpbill;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ivf.master.dto.AccountTitleRequest;
import com.ivf.master.entity.oracle.outpbill.OutpBillItems;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface OutpBillItemsMapper extends BaseMapper<OutpBillItems> {

    /**
     * 查询未维护的用友会计科目分类
     *
     * @return 未维护的用友会计科目分类
     */
    @Select("""
            select distinct obi.ITEM_CODE,
                            obi.ITEM_NAME,
                            obi.CLASS_ON_RECKONING,
                            ricd.CLASS_NAME,
                            oac.TJ_ACC_CODE,
                            oac.TJ_ACC_NAME,
                            oac.YY_ACC_CODE,
                            oac.YY_ACC_NAME
            from OUTP_BILL_ITEMS obi
                     left join outpbill.OUTP_ACC_CONTRAST oac on obi.ITEM_CODE = oac.ITEM_CODE
                     left join RECK_ITEM_CLASS_DICT ricd on obi.CLASS_ON_RECKONING = ricd.CLASS_CODE
            where obi.VISIT_DATE between #{startDate} and #{endDate}
              and obi.ITEM_CODE like #{itemCode}
              and obi.ITEM_NAME like #{itemName}
              and oac.YY_ACC_CODE is null
            """)
    IPage<AccountTitleRequest> getAccountTitle(Page<AccountTitleRequest> page, String itemCode, String itemName, Date startDate, Date endDate);

}
