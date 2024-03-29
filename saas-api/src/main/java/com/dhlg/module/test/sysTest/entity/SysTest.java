package com.dhlg.module.test.sysTest.entity;

import java.util.Date;
import java.math.BigDecimal;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_test")
public class SysTest implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
        *id
        */
        @ExcelIgnore
        @TableId("id")
        private String id;

        /**
         * 测试名
         */
        @ExcelProperty("测试名")
        @TableField("test_name")
        private String testName;
        /**
         * 创建时间
         */
        @ExcelProperty("创建时间")
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @TableField("create_time")
        private Date createTime;
        /**
         * 创建人
         */
        @ExcelProperty("创建人")
        @TableField("create_user")
        private String createUser;
        /**
         * 单价
         */
        @ExcelProperty("单价")
        @TableField("test_unit")
        private BigDecimal testUnit;
        /**
         * 总价
         */
        @ExcelProperty("总价")
        @TableField("test_total")
        private BigDecimal testTotal;
        /**
         * 测试类型
         */
        @ExcelProperty("测试类型")
        @TableField("test_type")
        private String testType;
        /**
         * 测试状态
         */
        @ExcelProperty("测试状态")
        @TableField("test_status")
        private String testStatus;

        /**
         * 个数
         */
        @ExcelProperty("个数")
        @TableField("test_num")
        private Float testNum;
        /**
         * 父id
         */
        @ExcelIgnore
        @TableField("parent_id")
        private String parentId;
        /**
         * 测试文本
         */
        @ExcelProperty("测试文本")
        @TableField("test_area")
        private String testArea;
}
