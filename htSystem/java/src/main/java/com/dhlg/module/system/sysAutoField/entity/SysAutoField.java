package com.dhlg.module.system.sysAutoField.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 *
 * </p>
 *
 * @author xu
 * @since 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_auto_field")
public class SysAutoField implements Serializable {

    private static final long serialVersionUID = 1L;

     /**
     *id
     */
    @TableId("id")
    private String id;

    /**
     * 字段小数点
     */
    @TableField("field_decimal")
    private Integer fieldDecimal;
    /**
     * 字段描述
     */
    @TableField("field_des")
    private String fieldDes;
    /**
     * 字段是否为空
     */
    @TableField("field_isNull")
    private String fieldIsNull;
    /**
     * 字段是否作为表格查询条件
     */
    @TableField("field_isquery")
    private String fieldIsquery;
    /**
     * 字段是否在表单中显示
     */
    @TableField("field_isShow_from")
    private String fieldIsShowFrom;
    /**
     * 字段是否在表格中显示
     */
    @TableField("field_isShow_table")
    private String fieldIsShowTable;
    /**
     * 字段长度
     */
    @TableField("field_length")
    private Integer fieldLength;
    /**
     * 字段名称
     */
    @TableField("field_name")
    private String fieldName;
    /**
     * 字段是否是主键
     */
    @TableField("field_primary")
    private String fieldPrimary;
    /**
     * 字段显示在页面上的状态：0文本，1长文本，2数字，3金额，4日期，5下拉框
     */
    @TableField("field_show_type")
    private String fieldShowType;
    /**
     * 字段类型
     */
    @TableField("field_type")
    private String fieldType;
    /**
     * 表id
     */
    @TableField("table_id")
    private String tableId;

}
