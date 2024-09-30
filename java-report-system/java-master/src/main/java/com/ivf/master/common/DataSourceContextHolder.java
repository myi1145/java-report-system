package com.ivf.master.common;

public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSource(String dataSourceName) {
        contextHolder.set(dataSourceName);
    }
    public void setDataSources(String dataSourceName) {
        contextHolder.set(dataSourceName);
    }

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }
}
