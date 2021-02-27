package com.dhlg.module.system.sysAutoTable.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.dhlg.module.system.sysAutoField.entity.SysAutoField;
import com.dhlg.module.system.sysAutoParam.entity.SysAutoParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

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
@TableName("sys_auto_table")
public class SysAutoTable implements Serializable{

    private static final long serialVersionUID = 1L;

     /**
     *id
     */
    @TableId("id")
    private String id;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("create_user")
    private String createUser;
    /**
     * 父表
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * 描述
     */
    @TableField("remark")
    private String remark;
    /**
     * 状态：0未生成，1已生成，2已删除
     */
    @TableField("status")
    private String status;
    /**
     * 表名
     */
    @TableField("table_name")
    private String tableName;
    /**
     * 表类型：0单表，1主表，2附表
     */
    @TableField("table_type")
    private String tableType;
    /**
     * 类型：模板1，模板2.。。。。。。
     */
    @TableField("type")
    private String type;

    /**
     * 是否为模板：0否1是
     */
    @TableField("is_template")
    private String isTemplate;

    /**
     * 模板名称
     */
    @TableField("temPlate_name")
    private String temPlateName;

    /**
     * 模板名称
     */
    @TableField("create_url")
    private String createUrl;

    /**
     * 模板名称
     */
    @TableField("api_url")
    private String apiUrl;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("update_time")
    private Date updateTime;
    /**
     * 更新人
     */
    @TableField("update_user")
    private String updateUser;

    @TableField(value = "children", exist = false) // 标记为非数据库字段
    private List<SysAutoTable> children;

    @TableField(exist = false) // 标记为非数据库字段
    private List<SysAutoField> autoFieldList;

    @TableField(exist = false) // 标记为非数据库字段
    private SysAutoParam autoParam;
}
