package com.dhlg.module.system.sysAutoTable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.common.utils.*;
import com.dhlg.module.system.sysAutoField.entity.SysAutoField;
import com.dhlg.module.system.sysAutoField.service.impl.SysAutoFieldServiceImpl;
import com.dhlg.module.system.sysAutoTable.entity.SysAutoTable;
import com.dhlg.module.system.sysAutoTable.dao.SysAutoTableMapper;
import com.dhlg.module.system.sysAutoTable.service.ISysAutoTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.common.utils.Parameter.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.dhlg.common.utils.mailUtils;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
@Service
public class SysAutoTableServiceImpl extends ServiceImpl<SysAutoTableMapper, SysAutoTable> implements ISysAutoTableService {

    @Autowired
    SysAutoTableMapper doMapper;

    @Autowired
    SysAutoFieldServiceImpl autoFieldService;

    @Autowired
    mailUtils mailUtils;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    private static final String TO = "1967368657@qq.com";
    private static final String SUBJECT = "测试邮件";
    private static final String CONTENT = "test content";


    @Override
    @Transactional
    public Result customSaveOrUpdate(SysAutoTable sysAutoTable) {
        //判断表是否存在
        boolean isexis = existboolean(sysAutoTable);
        if (isexis){
            return Result.error("表已经存在，请更改表名称重试");
        }

        //判断新增还是修改
        if (StringUtils.isBlank(sysAutoTable.getId())) {
            //新增
            sysAutoTable.setId(StringUtils.uuid());
            sysAutoTable.setCreateTime(DateUtils.getCurrentDate());
            sysAutoTable.setCreateUser(GetLoginUser.getCurrentUserId());
            sysAutoTable.setStatus("0");

            if(!save(sysAutoTable)){
                return Result.error(Dictionaries.SAVE_FAILED);
            }
            if (!StringUtils.isBlank(sysAutoTable.getAutoFieldList())){
                for (SysAutoField autoField : sysAutoTable.getAutoFieldList()) {
                    autoField.setId(StringUtils.uuid());
                    autoField.setTableId(sysAutoTable.getId());
                }
                autoFieldService.saveBatch(sysAutoTable.getAutoFieldList());
            }
            //创建表
            SysAutoTable sysAutoTable1 = changeTableData(sysAutoTable);
            creatTable(sysAutoTable1);
            return Result.success(Dictionaries.SAVE_SUCCESS);
        }
        sysAutoTable.setUpdateTime(DateUtils.getCurrentDate());
        sysAutoTable.setUpdateUser(GetLoginUser.getCurrentUserId());
        sysAutoTable.setStatus("0");
        if (!updateById(sysAutoTable)){
            return new Result("500","", Dictionaries.UPDATE_FAILED);
        }
        if (!StringUtils.isBlank(sysAutoTable.getAutoFieldList())){
            for (SysAutoField autoField : sysAutoTable.getAutoFieldList()) {
                autoField.setId(StringUtils.uuid());
                autoField.setTableId(sysAutoTable.getId());
            }

            QueryWrapper<SysAutoField> queryWrapper = new QueryWrapper<>();
            autoFieldService.remove(queryWrapper.eq("table_id", sysAutoTable.getId()));
            autoFieldService.saveBatch(sysAutoTable.getAutoFieldList());
        }

        return new Result("200","",Dictionaries.UPDATE_SUCCESS);
    }

    /**
     * 创建表语句
     */
    private void creatTable(SysAutoTable sysAutoTable1) {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(url,username,password);
            stmt = conn.createStatement();
            StringBuffer sql = new StringBuffer();
            sql.append("CREATE TABLE ").append(sysAutoTable1.getTableName()).append(" (id varchar(36) NOT NULL COMMENT '主键'");
            for (SysAutoField autoField : sysAutoTable1.getAutoFieldList()) {
                if (!"id".equals(autoField.getFieldName())){
                    sql.append(","+autoField.getFieldName()+" ");

                    if ("0".equals(autoField.getFieldShowLength())){
                        sql.append(autoField.getFieldType()+"("+autoField.getFieldLength()+","+autoField.getFieldDecimal()+") ");
                    }else {
                        sql.append(autoField.getFieldType()+"("+autoField.getFieldLength()+") ");
                    }

                    if ("1".equals(autoField.getFieldIsNull())){
                        sql.append(" not ");
                    }
                    sql.append(" NULL COMMENT '"+autoField.getFieldDes()+"'");
                    if ("0".equals(autoField.getFieldPrimary())){
                        sql.append(",PRIMARY KEY (`"+autoField.getFieldName()+"`) USING BTREE");
                    }
                }

            }
            sql.append(",PRIMARY KEY (`id`) USING BTREE)");
            stmt.execute(sql.toString());

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }

    /**
     * 改变数据
     */
    private SysAutoTable changeTableData(SysAutoTable sysAutoTable) {
        SysAutoTable object = (SysAutoTable)StringUtils.deepCloneObject(sysAutoTable);
        //改变对象的值
        if (StringUtils.isBlank(object)){
            List<SysAutoField> autoFieldList = object.getAutoFieldList();
            for (SysAutoField autoField : autoFieldList){
                //如果是符合的类型，，显示小数点
                if ("float".equals(autoField.getFieldType())||"decimal".equals(autoField.getFieldType())||"double".equals(autoField.getFieldType())){
                    autoField.setFieldShowType("1");
                }else {
                    autoField.setFieldShowType("0");
                }
            }
        }
        return object;
    }

    @Override
    @Transactional
    public Result delete(String id) {


        if (!removeById(id)){
            return new Result("500","", Dictionaries.DELETE_FAILED);
        }

        QueryWrapper<SysAutoField> queryWrapper = new QueryWrapper<>();
        autoFieldService.remove(queryWrapper.eq("table_id", id));
        return new Result("200","",Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public Result deleteBatch(List<String> ids) {
        if (!removeByIds(ids)){
            return new Result("500","", Dictionaries.DELETE_FAILED);
        }
        return new Result("200","",Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public Result queryByCondition(Parameter parameter) {
        parameter.setDefault();
        IPage<SysAutoTable> dataList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(dataList);
    }

    @Override
    public Result listFieldQuery(Map<String, Object> params) {
        Long number = Long.valueOf(String.valueOf(params.getOrDefault("number", 1)));
        Long size = Long.valueOf(String.valueOf(params.getOrDefault("size", 10)));
        IPage<SysAutoTable> dataList = doMapper.listFieldQuery(new Page(number, size), params);
        return new Result("200", dataList);
    }

    @Override
    public Result getNodeList() {

        List<SysAutoTable> list = list();
        List<SysAutoTable> rootNodes = InitTree.getRootNodes(list);
        return Result.success(rootNodes);
    }

    @Override
    public Result existable(SysAutoTable sysAutoTable) {

        boolean isexis = existboolean(sysAutoTable);

        if (!isexis){
            return Result.success("0");
        }
        return Result.error("1");
    }

    /*
    *返回 true为数据库没有该表
    */
    private boolean existboolean(SysAutoTable sysAutoTable) {

        String name = doMapper.getDatabaseName();
        int tablecoount = doMapper.existable(sysAutoTable.getTableName(),name);
        return 0 != tablecoount;
    }




    /**
     * 普通文本邮件测试
     *
     * @throws Exception
     */
    public void test() throws Exception {
        String to = TO;
        String subject = SUBJECT;
        String content = "内容";
        try {
            mailUtils.sendSimpleMail(to, subject, content);
            System.out.println("成功了");

        } catch (MailException e) {
            System.out.println("失败了");
            e.printStackTrace();
        }

    }


    /**
     * 带图片的邮件测试
     */
    public void test1() {
        String to = TO;
        String subject = SUBJECT;
        String rscId = "img";
        String content = "<html><body><img width='250px' src=\'cid:" + rscId + "\'></body></html>";
        // 此处为电脑系统路径
        String imgPath = "D:\\aaaa.PNG";
        try {
            mailUtils.sendInlineResourceMail(to, subject, content, imgPath, rscId);
            System.out.println("成功了");
        } catch (MessagingException e) {
            System.out.println("失败了");
            e.printStackTrace();
        }
    }


    /**
     * 带HTML邮件测试
     */
    public void test2() {
        String to = TO;
        String subject = SUBJECT;
        String content = "<html><head></head><body><h3>哈哈，什么都没有</h3></body></html>";
        try {
            mailUtils.sendHtmlMail(to, subject, content);
            System.out.println("成功了");
        } catch (MessagingException e) {
            System.out.println("失败了");
            e.printStackTrace();
        }
    }

    /**
     * 带附件的邮件测试
     */
    public void test3() {
        String to = TO;
        String subject = SUBJECT;
        String content = "内容";
        String imgPath = "D:\\简历--朱良.doc";
        try {
            mailUtils.sendAttachmentsMail(to, subject, content, imgPath);
            System.out.println("成功了");
        } catch (MessagingException e) {
            System.out.println("失败了");
            e.printStackTrace();
        }
    }


}
