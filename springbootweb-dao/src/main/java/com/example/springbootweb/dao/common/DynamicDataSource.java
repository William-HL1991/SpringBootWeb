package com.example.springbootweb.dao.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据源路由
 * @author william
 * @date 2020/7/23
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return MultipleDataSourceHelper.get();
    }
}
