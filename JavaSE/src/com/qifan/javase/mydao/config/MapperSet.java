package com.qifan.javase.mydao.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperSet {

    private MapperSet(){}

    private static List<MapperClass> mappers = new ArrayList<>();
    private static Map<String, MapperClass> mapperSet = new HashMap<>();

    public static void add(MapperClass mapperClass) {
        mapperSet.put(mapperClass.getId(),mapperClass);
        mappers.add(mapperClass);
    }

    public static MapperClass get(String id) {
        return mapperSet.get(id);
    }

    public static List getMappers() {
        return mappers;
    }
}
