package com.dhlg.common.code;


/**
 * 描述
 * User:whfch
 * Date:2020/5/7
 * Time:16:32
 */
public class test {

    public static void main(String []args){
        //生成模板
        codeAutoGenerationUtil test = new codeAutoGenerationUtil();
        String tableName = "sys_auto_field"; //表名称
        String packageName = "com.dhlg";//包名
        String projectName = "system";//模块名
        test.getTemplates(tableName,packageName,projectName);
//        System.out.println(test.getsrc(packageName,projectName,"appUser"));
    }
}
