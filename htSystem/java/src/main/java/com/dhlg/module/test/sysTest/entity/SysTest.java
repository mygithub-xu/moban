package com.dhlg.module.test.sysTest.entity;

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
@TableName("sys_test")
public class SysTest implements Serializable {

    private static final long serialVersionUID = 1L;

     /**
     *id
     */
    @TableId("id")
    private String id;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("create_user")
    private String createUser;
    /**
     * 父id
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * 测试文本
     */
    @TableField("test_area")
    private String testArea;
    /**
     * 测试名
     */
    @TableField("test_name")
    private String testName;
    /**
     * 个数
     */
    @TableField("test_num")
    private Float testNum;
    /**
     * 测试状态
     */
    @TableField("test_status")
    private String testStatus;
    /**
     * 总价
     */
    @TableField("test_total")
    private BigDecimal testTotal;
    /**
     * 测试类型
     */
    @TableField("test_type")
    private String testType;
    /**
     * 单价
     */
    @TableField("test_unit")
    private BigDecimal testUnit;

    /**
     * 时间区域start
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @TableField(exist = false)
    private Date createTimeFrom;

    /**
     * 时间区域end
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @TableField(exist = false)
    private Date createTimeTo;
}
