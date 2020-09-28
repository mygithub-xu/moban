package com.dhlg.common.code;

/**
 * 描述
 * User:whfch
 * Date:2020/5/8
 * Time:11:33
 */
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


    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public void setFieldComment(String fieldComment) {
        this.fieldComment = fieldComment;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }



    public String getFieldName() {
        return fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public String getFieldComment() {
        return fieldComment;
    }

    public String getAttrType() {
        return attrType;
    }

    public String getAttrName() {
        return attrName;
    }


    public String getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(String fieldLength) {
        this.fieldLength = fieldLength;
    }
}
