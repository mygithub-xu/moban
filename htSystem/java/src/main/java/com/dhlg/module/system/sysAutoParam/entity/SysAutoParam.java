package com.dhlg.module.system.sysAutoParam.entity;


import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.dhlg.module.system.sysAutoFieldParam.entity.SysAutoFieldParam;
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
@TableName("sys_auto_param")
public class SysAutoParam implements Serializable {

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
     * 布局类型
     */
    @TableField("layout_type")
    private String layoutType;
    /**
     * 编辑区域（0：false，1：true）
     */
    @TableField("show_edit")
    private String showEdit;
    /**
     * 分页区域（0：false，1：true）
     */
    @TableField("show_page")
    private String showPage;
    /**
     * 查询区域（0：false，1：true）
     */
    @TableField("show_query")
    private String showQuery;
    /**
     * 表格区域（0：false，1：true）
     */
    @TableField("show_table_data")
    private String showTableData;

    @TableField(exist = false)
    private Boolean isShowQuery;

    @TableField(exist = false)
    private Boolean isShowTableData;

    @TableField(exist = false)
    private Boolean isShowPage;

    @TableField(exist = false)
    private List<SysAutoFieldParam> tableList;

    @TableField(exist = false)
    private List<SysAutoFieldParam> queryList;
}
