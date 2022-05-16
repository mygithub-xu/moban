package com.dhlg.module.system.sysAutoFieldParam.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("sys_auto_field_param")
public class SysAutoFieldParam implements Serializable {

    private static final long serialVersionUID = 1L;

     /**
     *id
     */
    @TableId("id")
    private String id;

    /**
     * 种类（1.查询区域，2表格区域）
     */
    @TableField("layout_type")
    private String layoutType;
    /**
     * 与sys_auto_param关联
     */
    @TableField("param_id")
    private String paramId;
    /**
     * 名称
     */
    @TableField("title")
    private String title;
    /**
     * 查询页面元素（1.输入框，2.下拉框，3.日期下拉框，4.按钮）
     * 表格页面元素（1.表格操作按钮）
     */
    @TableField("type")
    private String type;
    /**
     * 关联的字段
     */
    @TableField("value")
    private String value;
    /**
     * 排序
     */
    @TableField("param_index")
    private String paramIndex;

    /**
     * 对应value的字段名称：如xxx_yyy
     */
    @TableField(exist = false)
    private String fieldName;

    /**
     * 对应value的字段名称驼峰xxxYyy
     */
    @TableField(exist = false)
    private String fieldNameHump;

    /**
     * 字典名称
     */
    @TableField(exist = false)
    private String dicType;

    /**
     * 是否是选择框
     */
    @TableField(exist = false)
    private String isSelect;

}
