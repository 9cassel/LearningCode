package com.qifan.javase.mydao.datasource;

import com.qifan.javase.mydao.config.DatasourceType;
import com.zaxxer.hikari.HikariDataSource;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.Properties;

public class MyDataSource {


    private String driverClass;
    private String url;
    private String username;
    private String password;
    private Properties prop = new Properties();

    public MyDataSource(String path) {
        initProperties(path);
    }

    private void initProperties(String path) {
        prop = loadProp(path);
        if (prop == null) {
            return;
        }
        driverClass = prop.getProperty(DatasourceType.DRIVER_CLASS);
        url = prop.getProperty(DatasourceType.URL);
        username = prop.getProperty(DatasourceType.USER);
        password = prop.getProperty(DatasourceType.PASSWORD);
    }

    public Properties loadProp(String path) {
        InputStream is;
        try {
            is = MyDataSource.class.getResourceAsStream(path);
            prop.load(is);
            return prop;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("properties加载失败");
            return null;
        }
    }

    public Connection getConnection() {
        try {
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setJdbcUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            Connection conn = dataSource.getConnection();
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public String getDriverClass() {
        return driverClass;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
