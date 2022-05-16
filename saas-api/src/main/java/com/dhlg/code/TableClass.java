package com.dhlg.code;

import lombok.Data;

/**
 * 描述
 * User:whfch
 * Date:2020/5/8
 * Time:11:33
 */
@Data
public class TableClass {

    /** 字段名称 */
    private String fieldName;

    /** 字段类型 */
    private String fieldType;

    /** 字段长度 */
    private String fieldLength;

    /** 列描述 */
    private String fieldComment;

    /** Java属性类型 */
    private String attrType;

    /** 驼峰(第一个字母小写)，如：user_name => userName */
    private String attrName;
}
