package com.ivf.master.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置类，用于配置不同的数据源。
 */
@Configuration
public class DataSourceConfig {

    /**
     * 配置天津测试数据库的数据源
     *
     * @return 天津测试数据库的Druid数据源
     */
    @Bean(name = "tjtest")
    @ConfigurationProperties(prefix = "spring.datasource.tjtest")
    public DruidDataSource tjtest() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置Web报表MySQL的数据源
     *
     * @return Web报表MySQL的Druid数据源
     */
    @Bean(name = "webmysql")
    @ConfigurationProperties(prefix = "spring.datasource.webmysql")
    public DruidDataSource webmysql() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置天津测试数据库的数据源
     *
     * @return 天津测试数据库的Druid数据源
     */
    @Bean(name = "tjivf")
    @ConfigurationProperties(prefix = "spring.datasource.tjivf")
    public DruidDataSource tjivf() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置本地MySQL的数据源
     *
     * @return 本地MySQL的Druid数据源
     */
    @Bean(name = "mysql")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DruidDataSource mysql() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置揭阳正式数据库的数据源
     *
     * @return 揭阳正式数据库的Druid数据源
     */
    @Bean(name = "jyhis")
    @ConfigurationProperties(prefix = "spring.datasource.jyhis")
    public DruidDataSource jyhis() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置昆明正式数据库的数据源
     *
     * @return 昆明正式数据库的Druid数据源
     */
    @Bean(name = "kmhis")
    @ConfigurationProperties(prefix = "spring.datasource.kmhis")
    public DruidDataSource kmhis() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置湛江正式数据库的数据源
     *
     * @return 湛江正式数据库的Druid数据源
     */
    @Bean(name = "zjhis")
    @ConfigurationProperties(prefix = "spring.datasource.zjhis")
    public DruidDataSource zjhis() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置昆明互创数据库的数据源
     *
     * @return 湛江正式数据库的Druid数据源
     */
    @Bean(name = "kmhc")
    @ConfigurationProperties(prefix = "spring.datasource.kmhc")
    public DruidDataSource kmhc() {
        return DruidDataSourceBuilder.create().build();
    }

//    /**
//     * 2024-06-17 GJH
//     * 配置天津用友据库的数据源
//     *
//     * @return 天津正式数据库的Druid数据源
//     */
//    @Bean(name = "tjyy")
//    @ConfigurationProperties(prefix = "spring.datasource.tjyy")
//    public DruidDataSource tjyy() {
//        return DruidDataSourceBuilder.create().build();
//    }
    /**
     * 动态数据源
     */
    @Bean(name = "dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource(
            @Qualifier("webmysql") DataSource webmysql,
            @Qualifier("tjivf") DataSource tjivf,
            @Qualifier("jyhis") DataSource jyhis,
            @Qualifier("tjtest") DataSource tjtest,
            @Qualifier("kmhis") DataSource kmhis,
            @Qualifier("zjhis") DataSource zjhis,
            @Qualifier("mysql") DataSource mysql,
            @Qualifier("kmhc") DataSource kmhc
    ) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put("webmysql", webmysql);
        targetDataSource.put("tjivf", tjivf);
        targetDataSource.put("jyhis", jyhis);
        targetDataSource.put("kmhis", kmhis);
        targetDataSource.put("zjhis", zjhis);
        targetDataSource.put("tjtest", tjtest);
        targetDataSource.put("mysql", mysql);
        targetDataSource.put("kmhc", kmhc);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSource);

        dynamicDataSource.setDefaultTargetDataSource(mysql);
        return dynamicDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("dynamicDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
