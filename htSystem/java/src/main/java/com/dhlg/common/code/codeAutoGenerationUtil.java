package com.dhlg.common.code;


import com.dhlg.common.utils.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * 描述 代码自动生成
 * User:whfch
 * Date:2020/5/7
 * Time:14:46
 */
@Configuration
public class codeAutoGenerationUtil {
//    @Value("${spring.datasource.url}")
//    private  String URL;
    //获得url
    private final String URL = "jdbc:mysql://localhost:3306/houtaisql?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";

    //获得驱动
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    //数据库账号
    private final String USERNAME = "root";

    //数据库密码
    private final String PASSWORD = "123456";
//    private final String PASSWORD = "123";

    private String templateDir = "\\src\\main\\resources\\templates\\";

    private String sourcePath = System.getProperty("user.dir")+templateDir;


    //自动生成代码
    public void getTemplates(String tableName, String packageName, String projectName) {

        System.out.println("获取到路径"+URL);
        //将app_user 换成 驼峰命名 appUser
        String domainName= StringUtils.underscoreToCamelCase(tableName);
        //首字母大写
        String DomainName= StringUtils.upperFirstCase(domainName);
        //形成输出路径
        String targetPath=getsrc(packageName,projectName,domainName);
        //根据表面获取字段
        List<TableClass> tableClassList=getTableClassValue(tableName);

        //根据模板生成文件
        Map<String,Object> map = new HashMap();
        map.put("controller.java.vm","controller/" + DomainName + "Controller.java");
        map.put("service.java.vm","service/"+"I" + DomainName + "Service.java");
        map.put("serviceImpl.java.vm","service/impl/" + DomainName + "ServiceImpl.java");
        map.put("mapper.java.vm","dao/" + DomainName + "Mapper.java");
        map.put("entity.java.vm","entity/" + DomainName + ".java");
        map.put("mapper.xml.vm","dao/xml/" + DomainName + "Mapper.xml");
        map.put("templates2.vue.vm",domainName +"/" + domainName + "List.vue");
        map.put("templates1.vue.vm",domainName +"/" + domainName + "List.vue");
        for(String templateFile:map.keySet()){
            String targetFile = (String) map.get(templateFile);
            Properties pro = new Properties();
            //设置文本传入编码类型
            pro.setProperty(Velocity.INPUT_ENCODING, "UTF-8");

            pro.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, sourcePath);

            VelocityEngine ve = new VelocityEngine(pro);

            VelocityContext context = new VelocityContext();

            context.put("DomainName",DomainName);
            context.put("domainName",domainName);
            context.put("tableName",tableName);
            context.put("packageName",packageName);
            context.put("projectName",projectName);
            context.put("tableClassList",tableClassList);

            Template t = ve.getTemplate(templateFile, "UTF-8");
            try {
                File file = new File(targetPath, targetFile);
                if (!file.getParentFile().exists())
                    file.getParentFile().mkdirs();
                if (!file.exists())
                    file.createNewFile();

                FileOutputStream outStream = null;

                outStream = new FileOutputStream(file);

                OutputStreamWriter writer = new OutputStreamWriter(outStream,
                        "UTF-8");
                BufferedWriter sw = new BufferedWriter(writer);
                t.merge(context, sw);
                sw.flush();
                sw.close();
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("成功生成Java文件:"
                    + (targetPath + targetFile).replaceAll("/", "\\\\"));
        }

    }

    //获取表结构
    private  List<TableClass> getTableClassValue(String tableName){
        List<TableClass> tableList=new ArrayList<>();
        Connection connection=null;
        PreparedStatement pre=null;
        ResultSet resultSet=null;
        //获取数据库的链接
        try {
            //连接数据库
            connection = getConnection();

            String sql="SELECT COLUMN_NAME as fieldName,column_type as fieldLength, DATA_TYPE as fieldType,COLUMN_COMMENT as fieldComment FROM INFORMATION_SCHEMA. COLUMNS WHERE" +
                    " table_schema = 'houtaisql'" +
                    " AND " +
                    "table_name = '"+tableName+"'" ;
            pre = connection.prepareStatement(sql);
            resultSet = pre.executeQuery();

            while (resultSet.next()){
                TableClass tableClass = new TableClass();
                String fieldName = resultSet.getString("fieldName");
                //字段名称
                tableClass.setFieldName(fieldName);
                //字段类型
                tableClass.setFieldType(resultSet.getString("fieldType"));
                //列描述
                tableClass.setFieldComment(resultSet.getString("fieldComment"));
                //Java属性名称驼峰写法
                tableClass.setAttrName(StringUtils.underscoreToCamelCase(fieldName));
                //Java属性类型
                tableClass.setAttrType(CommonMap.javaTypeMap.get(tableClass.getFieldType()));
                //java字段长度
                String ss=resultSet.getString("fieldLength").equals("varchar(36)")?"1":"0";
                tableClass.setFieldLength(ss) ;
                System.out.println(tableClass.getFieldName()+" : "+ tableClass.getFieldLength());
                tableList.add(tableClass);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (pre != null) {
                    pre.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }catch (Exception e) {
                    e.printStackTrace();
                }
        }

        return tableList;

    }


    public  Connection getConnection()  throws Exception  {

        Class.forName(DRIVER);

        Connection connection= DriverManager.getConnection(URL, USERNAME, PASSWORD);

        connection.setAutoCommit(true);

        return connection;

    }


    public String getsrc(String packageName, String projectName, String domainName) {
             return System.getProperty("user.dir")
                     + "\\src\\" + "main\\"+ "java\\"
            + packageName.replace(".", "\\")+ "\\module\\"
                     +projectName+ "\\"+domainName;
    }

}
