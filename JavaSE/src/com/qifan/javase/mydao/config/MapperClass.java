package com.qifan.javase.mydao.config;

import java.util.Map;

public class MapperClass {

    private String type;
    private String id;
    private Class resultType;
    private String sqlContent;
    private Map<String,Integer> parameter;

    public Map<String, Integer> getParameter() {
        return parameter;
    }

    public void setParameter(Map<String, Integer> parameter) {
        this.parameter = parameter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSqlContent() {
        return sqlContent;
    }

    public void setSqlContent(String sqlContent) {
        this.sqlContent = sqlContent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Class getResultType() {
        return resultType;
    }

    public void setResultType(Class resultType) {
        this.resultType = resultType;
    }

    @Override
    public String toString() {
        return "MapperClass{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", resultType=" + resultType +
                ", sqlContent='" + sqlContent + '\'' +
                '}';
    }
}
