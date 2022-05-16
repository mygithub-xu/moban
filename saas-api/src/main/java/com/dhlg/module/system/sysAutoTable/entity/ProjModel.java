package com.dhlg.module.system.sysAutoTable.entity;

import lombok.Data;

@Data
public class ProjModel {
    //表id
    private String tableId;
    //包名 如com.dhlg
    private String packageName;
    //分类名 如system
    private String projectName;
    //表名 如sys_test
    private String table_name;

    //包名 如com\dhlg
    private String package_name;
    //表名驼峰式 如sysTest
    private String tableName;
    //表名首字母大写驼峰式 如SysTest
    private String _TableName;
    //生成文件路径
    private String createUrl;
    //接口名
    private String apiUrl;

}
