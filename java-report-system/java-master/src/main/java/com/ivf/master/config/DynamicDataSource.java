package com.ivf.master.config;

import com.ivf.master.common.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("动态获取数据源--{}", DataSourceContextHolder.getDataSource());
        return DataSourceContextHolder.getDataSource();
    }
}
