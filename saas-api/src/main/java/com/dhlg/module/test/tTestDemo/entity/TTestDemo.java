package com.dhlg.module.test.tTestDemo.entity;

import java.util.Date;
import java.math.BigDecimal;

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
@TableName("t_test_demo")
public class TTestDemo implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
        *id
        */
        @ExcelProperty("id")
        @TableId("id")
        private String id;

        /**
         * 
         */
        @ExcelProperty("name")
        @TableField("name")
        private String name;
        /**
         * 
         */
        @ExcelProperty("age")
        @TableField("age")
        private Integer age;
        /**
         * 
         */
        @ExcelProperty("hobby")
        @TableField("hobby")
        private String hobby;
}
