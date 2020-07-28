package com.dhlg.module.test.sysTest.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
public class ExportSysTest extends BaseRowModel {



//    @ExcelProperty(value = {"主键ID"}, index = 0)
//    private String id;

//    /**
//     * 创建时间
//     */
//    @ExcelProperty(value = {"创建时间"}, index = 0)
//    private Date createTime;

    /**
     * 创建人
     */

    @ExcelProperty(value = {"创建人"}, index = 0)
    private String createUser;

    /**
     * 测试名
     */

    @ExcelProperty(value = {"测试名"}, index = 1)
    private String testName;

    /**
     * 测试状态
     */

    @ExcelProperty(value = {"测试状态"}, index = 2)
    private String testStatus;

    /**
     * 文本域
     */

    @ExcelProperty(value = {"测试类型"}, index = 3)
    private String testType;

    /**
     * 文本域
     */

    @ExcelProperty(value = {"文本域"}, index = 4)
    private String testArea;

}
