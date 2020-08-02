package com.example.springbootweb.dao.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author william
 * @date 2020/7/23
 */
@Configuration
public class WebDataSource {

    // 主数据库
    @Bean("masterDataSource")
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    // 从数据库
    @Bean("slaveDataSource")
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    // 数据路由
    @Bean
    @Primary
    @DependsOn({"masterDataSource", "slaveDataSource"})
    public DataSource dynamicDatasource() {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(MultipleDataSourceHelper.MASTER, masterDataSource());
        dataSourceMap.put(MultipleDataSourceHelper.SLAVE, slaveDataSource());
        DynamicDataSource dds = new DynamicDataSource();
        dds.setTargetDataSources(dataSourceMap);
        dds.setDefaultTargetDataSource(masterDataSource()); // 没找到 key 的时候，默认使用 master
        return dds;
    }

}
