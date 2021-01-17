package com.dhlg.code;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述
 * User:whfch
 * Date:2020/5/8
 * Time:14:31
 */
public class CommonMap {

    /** 状态编码转换 */
    public static Map<String, String> javaTypeMap = new HashMap<String, String>();

    static
    {
        initJavaTypeMap();
    }

    /**
     * 返回状态映射
     */
    public static void initJavaTypeMap()
    {
        javaTypeMap.put("tinyint", "Integer");
        javaTypeMap.put("smallint", "Integer");
        javaTypeMap.put("float", "Float");
        javaTypeMap.put("int", "Integer");
        javaTypeMap.put("interger", "Integer");
        javaTypeMap.put("bigint", "Long");
        javaTypeMap.put("double", "Double");
        javaTypeMap.put("decimal", "BigDecimal");
        javaTypeMap.put("bit", "Boolean");
        javaTypeMap.put("char", "String");
        javaTypeMap.put("varchar", "String");
        javaTypeMap.put("tinytext", "String");
        javaTypeMap.put("text", "String");
        javaTypeMap.put("mediumtext", "String");
        javaTypeMap.put("longtext", "String");
        javaTypeMap.put("date", "Date");
        javaTypeMap.put("datetime", "Date");
        javaTypeMap.put("timestamp", "Date");
    }
}
