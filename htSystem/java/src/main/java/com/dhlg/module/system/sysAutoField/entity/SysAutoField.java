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
     * 表id
     */
    @TableField("table_id")
    private String tableId;
    /**
     * 字段名称
     */
    @TableField("field_name")
    private String fieldName;

    /**
     * 字段描述
     */
    @TableField("field_des")
    private String fieldDes;
    /**
     * 字段长度
     */
    @TableField("field_length")
    private Integer fieldLength;
    /**
     * 字段类型
     */
    @TableField("field_type")
    private String fieldType;

    /**
     * 字段类型转java
     */
    @TableField(exist = false)
    private String fieldTypeToJava;

    /**
     * 字段小数点
     */
    @TableField("field_decimal")
    private Integer fieldDecimal;
    /**
     * 字段是否可以为空，1是非空，0是空
     */
    @TableField("field_isNull")
    private String fieldIsNull;
    /**
     * 字段是否是主键,0是主键，1不是主键
     */
    @TableField("field_primary")
    private String fieldPrimary;

    /**
     * 排序位置
     */
    @TableField("field_index")
    private String fieldIndex;
    /**
     * 字典id
     */
    @TableField("dic_id")
    private String dicId;
    /**
     * 是否关联表（0不关联，1关联）
     */
    @TableField("field_is_be_related")
    private String fieldIsBeRelated;
    /**
     * 关联表名称
     */
    @TableField("field_related_table_name")
    private String fieldRelatedTableName;
    /**
     * 关联表字段
     */
    @TableField("field_related_field")
    private String fieldRelatedField;
    /**
     * 关联表显示字段
     */
    @TableField("field_related_field_show")
    private String fieldRelatedFieldShow;
    /**
     * 是否显示小数点 0显示，1不显示
     */
    @TableField(exist = false)
    private String fieldShowType;

    /**
     * 字段名称驼峰式
     */
    @TableField(exist = false)
    private String fieldNameHump;

    /**
     * 是否为空
     */
    @TableField(exist = false)
    private Boolean fieldIsNullBoo;

    /**
     * 是否为主键
     */
    @TableField(exist = false)
    private Boolean fieldPrimaryBoo;

    /**
     * 是否为空
     */
    @TableField(exist = false)
    private Boolean fieldIsBeRelatedBoo;

    /**
     * 字典类型
     */
    @TableField(exist = false)
    private String dicType;

    /**
     * 接口路径
     */
    @TableField(exist = false)
    private String apiUrl;

    /**
     * 关联表字段驼峰式
     */
    @TableField("field_related_field")
    private String fieldRelatedFieldHump;
    /**
     * 关联表显示字段驼峰式
     */
    @TableField("field_related_field_show")
    private String fieldRelatedFieldShowHump;

}
