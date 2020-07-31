package com.qifan.javase.mydao.config;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapperClassSqlParse {

    private static Pattern parameterPattern = Pattern.compile("[#][{](.+?)[}]");

    public static void parseParameter(String sql, MapperClass mapper) {
        Matcher matcher = parameterPattern.matcher(sql);
        HashMap<String, Integer> parameter = new HashMap<>();
        int i = 1;
        while (matcher.find()) {
            String parameterName = matcher.group(1);
            parameter.put(parameterName, i++);
        }
        mapper.setParameter(parameter);
        String newSql = matcher.replaceAll(" ? ");
        mapper.setSqlContent(newSql);
    }

}
