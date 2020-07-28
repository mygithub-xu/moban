package com.dhlg.module.system.sysTable.entity;

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
@TableName("sys_table")
public class SysTable implements Serializable {

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
     * 主表id
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * 表名
     */
    @TableField("table_name")
    private String tableName;
    /**
     * 备注
     */
    @TableField("table_remark")
    private String tableRemark;
    /**
     * 表类型
     */
    @TableField("table_type")
    private String tableType;
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

}
