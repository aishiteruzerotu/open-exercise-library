package com.nf.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.nf.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataSourceUtils {
    private static DataSource dataSource;
    static {
        InputStream inputStream = DataSourceUtils.class
                .getClassLoader()
                .getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static SqlExecutorEx getSqlExecutorEx(){
        return new SqlExecutorEx(getDataSource());
    }

    public static SqlExecutor getSqlExecutor(){
        return new SqlExecutor(getDataSource());
    }

    public static Query getQuery(){
        return new QueryExecutor(getDataSource());
    }

    public static Update getUpdate(){
        return new UpdateExecutor(getDataSource());
    }
}
