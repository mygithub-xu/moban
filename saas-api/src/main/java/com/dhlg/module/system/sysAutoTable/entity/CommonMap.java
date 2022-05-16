package com.dhlg.module.system.sysAutoTable.entity;

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
        javaTypeMap.put("FLOAT", "Float");
        javaTypeMap.put("INT", "Integer");
        javaTypeMap.put("INTEGER", "Integer");
        javaTypeMap.put("BIGINT", "Long");
        javaTypeMap.put("DOUBLE", "Double");
        javaTypeMap.put("DECIMAL", "BigDecimal");
        javaTypeMap.put("BIT", "Boolean");
        javaTypeMap.put("CHAR", "String");
        javaTypeMap.put("VARCHAR", "String");
        javaTypeMap.put("TINYTEXT", "String");
        javaTypeMap.put("TEXT", "String");
        javaTypeMap.put("MEDIUMTEXT", "String");
        javaTypeMap.put("LONGTEXT", "String");
        javaTypeMap.put("DATE", "Date");
        javaTypeMap.put("DATETIME", "Date");
        javaTypeMap.put("TIMESTAMP", "Date");

        javaTypeMap.put("float", "Float");
        javaTypeMap.put("int", "Integer");
        javaTypeMap.put("integer", "Integer");
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
