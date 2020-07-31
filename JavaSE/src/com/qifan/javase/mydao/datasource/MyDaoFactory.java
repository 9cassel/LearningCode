package com.qifan.javase.mydao.datasource;

import com.qifan.javase.mydao.config.MapperClass;
import com.qifan.javase.mydao.config.MapperClassParse;
import com.qifan.javase.mydao.config.MapperSet;
import com.qifan.javase.mydao.sqlexe.Executor;

import java.util.List;

public class MyDaoFactory {

    private static MyDataSource dataSource;
    private static Executor executor;
    static {
        initDataSource(null);
        scanMapperClass("/mydao.xml");
        executor = new Executor(dataSource);
    }

    public static void initDataSource(String jdbcPropertiesPath) {
        if (null == jdbcPropertiesPath || "".equals(jdbcPropertiesPath.trim())) {
            jdbcPropertiesPath = "/jdbc.properties";
        }
        dataSource = new MyDataSource(jdbcPropertiesPath);
    }

    private static void scanMapperClass(String xmlPath) {
        MapperClassParse.parseXml(xmlPath);
    }

    public static void selectAll(String sqlId,  Object parameter) {
        MapperClass mapper = MapperSet.get(sqlId);
        List list = executor.selectAll(mapper , parameter);
        for (Object obj :
                list) {
            System.out.println(obj);
        }
    }

    public static void select(String sqlId, Object parameter) {
        MapperClass mapper = MapperSet.get(sqlId);
        List list = executor.selectAll(mapper , parameter);
        if (list.size() > 0) {
            System.out.println(list.get(0));
        }
    }

    public static void update(String sqlId, Object parameter) {
        MapperClass mapper = MapperSet.get(sqlId);
        int row = executor.update(mapper , parameter);
        System.out.println(row);
    }

    public static void main(String[] args) {
        MyDaoFactory.selectAll("sql1",null);
    }

}
