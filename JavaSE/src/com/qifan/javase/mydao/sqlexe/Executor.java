package com.qifan.javase.mydao.sqlexe;

import com.qifan.javase.mydao.bean.Student;
import com.qifan.javase.mydao.config.MapperClass;
import com.qifan.javase.mydao.datasource.MyDataSource;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Executor {

    private MyDataSource dataSource;

    public Executor(MyDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int update(MapperClass mapper, Object objects) {
        Connection conn = dataSource.getConnection();
        String sql = mapper.getSqlContent();
        Class clazz = mapper.getResultType();
        Map<String, Integer> parameter = mapper.getParameter();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);


            int i = ps.executeUpdate();
            return i;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }
    }

    public List selectAll(MapperClass mapper, Object objects) {

        Connection conn = dataSource.getConnection();
        String sql = mapper.getSqlContent();
        Class clazz = mapper.getResultType();
        Map<String, Integer> parameter = mapper.getParameter();
        List result = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if (parameter != null && parameter.size() > 0) {
                if (objects.getClass().isPrimitive() || objects.getClass().getSuperclass() == Number.class || objects.getClass() == String.class) {
                    ps.setObject(1, objects);
                } else {
                    for (Map.Entry<String, Integer> entry :
                            parameter.entrySet()) {
                        String parameterName = entry.getKey();
                        int pos = entry.getValue();
                        Field field = objects.getClass().getDeclaredField(parameterName);

                    }
                }
            }

            ResultSet resultSet = ps.executeQuery();
            Field[] fields = clazz.getDeclaredFields();

            while (resultSet.next()){
                Object obj = clazz.newInstance();
                for (Field field :
                        fields) {
                    String name = field.getName();
                    Class type = field.getType();
                    Object columnValue = resultSet.getObject(name);
                    Object value = parseStringValue(type, columnValue.toString());
                    if (value != null) {
                        field.setAccessible(true);
                        field.set(obj, value);
                    }
                }
                result.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Object parseStringValue(Class type, String value) {
        if (type == Integer.TYPE) {
            return Integer.parseInt(value);
        } else if (type == Short.TYPE) {
            return Short.parseShort(value);
        } else if (type == Long.TYPE) {
            return Long.parseLong(value);
        } else if (type == Double.TYPE) {
            return Double.parseDouble(value);
        } else if (type == Float.TYPE) {
            return Float.parseFloat(value);
        } else if (type == Byte.TYPE) {
            return Byte.parseByte(value);
        } else if (type == Character.TYPE) {
            return value.charAt(0);
        } else {
            return value;
        }
    }

}
