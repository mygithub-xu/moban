package com.dhlg.module.test.sysTest.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.dhlg.module.test.sysTestDetail.entity.SysTestDetail;
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
@TableName("sys_test")
public class SysTest  implements Serializable {

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
     * 测试名
     */

    @TableField("test_name")
    private String testName;

    /**
     * 测试状态
     */

    @TableField("test_status")
    private String testStatus;

    /**
     * 测试类型
     */

    @TableField("test_type")
    private String testType;

    /**
     * 文本域
     */

    @TableField("test_area")
    private String testArea;

    /**
     * 父id
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 下级list
     */
    @TableField(exist=false)
    private List<SysTest> children;

    @TableField(exist=false)
    private List<SysTestDetail> testSysTetailList;
}
