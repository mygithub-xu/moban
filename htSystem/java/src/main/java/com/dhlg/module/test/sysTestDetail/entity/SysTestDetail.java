package com.dhlg.module.test.sysTestDetail.entity;

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
@TableName("sys_test_detail")
public class SysTestDetail implements Serializable {

    private static final long serialVersionUID = 1L;

     /**
     *id
     */
    @TableId("id")
    private String id;

    /**
     * 父id
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * 名称
     */
    @TableField("name")
    private String name;
    /**
     * 状态
     */
    @TableField("status")
    private String status;
    /**
     * 钱
     */
    @TableField("money")
    private BigDecimal money;

}
