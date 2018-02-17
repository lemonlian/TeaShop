package com.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * mysql 读写分离配置
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSouce();
    }
}
