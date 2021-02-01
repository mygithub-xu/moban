package com.dhlg.module.system.sysAutoTable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.system.sysAutoTable.entity.CommonMap;
import com.dhlg.utils.*;
import com.dhlg.module.system.sysAutoField.entity.SysAutoField;
import com.dhlg.module.system.sysAutoField.service.impl.SysAutoFieldServiceImpl;
import com.dhlg.module.system.sysAutoFieldParam.entity.SysAutoFieldParam;
import com.dhlg.module.system.sysAutoFieldParam.service.impl.SysAutoFieldParamServiceImpl;
import com.dhlg.module.system.sysAutoParam.entity.SysAutoParam;
import com.dhlg.module.system.sysAutoParam.service.impl.SysAutoParamServiceImpl;
import com.dhlg.module.system.sysAutoTable.entity.ProjModel;
import com.dhlg.module.system.sysAutoTable.entity.SysAutoTable;
import com.dhlg.module.system.sysAutoTable.dao.SysAutoTableMapper;
import com.dhlg.module.system.sysAutoTable.service.ISysAutoTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.Parameter.Parameter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.dhlg.utils.mailUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.*;
import java.util.*;

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
    SysAutoFieldParamServiceImpl fieldParamService;

    @Autowired
    SysAutoParamServiceImpl paramService;

    @Autowired
    mailUtils mailUtils;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;
    @Value("${common.templateUrl}")
    private String templateUrl;

    private static final String TO = "1967368657@qq.com";
    private static final String SUBJECT = "测试邮件";
    private static final String CONTENT = "test content";


    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    Connection conn = null;
    Statement stmt = null;

    @Override
    @Transactional
    public Result customSaveOrUpdate(SysAutoTable sysAutoTable) {

        //判断新增还是修改
        if (StringUtils.isBlank(sysAutoTable.getId())) {
            //新增
            //判断表是否存在
            boolean isexis = existboolean(sysAutoTable);
            if (isexis){
                return Result.error("表已经存在，请更改表名称重试");
            }

            //插入数据
            sysAutoTable.setId(StringUtils.uuid());
            sysAutoTable.setCreateTime(DateUtils.getCurrentDate());
            sysAutoTable.setCreateUser(GetLoginUser.getCurrentUserId());
            sysAutoTable.setStatus("0");

            //批量保存明细
            saveDetails(sysAutoTable,true);

            //创建表
            SysAutoTable sysAutoTable1 = changeTableData(sysAutoTable);
            creatTable(sysAutoTable1);
            if(!save(sysAutoTable)){
                return Result.error(Dictionaries.SAVE_FAILED);
            }
            return Result.success(Dictionaries.SAVE_SUCCESS);
        }

        //更新
        sysAutoTable.setUpdateTime(DateUtils.getCurrentDate());
        sysAutoTable.setUpdateUser(GetLoginUser.getCurrentUserId());
        sysAutoTable.setStatus("0");
        saveDetails(sysAutoTable,false);

        //删除表
        SysAutoTable oldTable = getById(sysAutoTable.getId());
        doMapper.deleteTable(oldTable.getTableName());
        //创建表
        SysAutoTable sysAutoTable1 = changeTableData(sysAutoTable);
        creatTable(sysAutoTable1);
        if (!updateById(sysAutoTable)){
            return new Result("500","", Dictionaries.UPDATE_FAILED);
        }
        return new Result("200","",Dictionaries.UPDATE_SUCCESS);
    }

    private void saveDetails(SysAutoTable sysAutoTable,boolean isAdd) {
        //保存明细
        if (!StringUtils.isBlank(sysAutoTable.getAutoFieldList())){
            for (SysAutoField autoField : sysAutoTable.getAutoFieldList()) {
                autoField.setId(StringUtils.uuid());
                autoField.setTableId(sysAutoTable.getId());
                autoField.setFieldIsNull(autoField.getFieldIsNullBoo()?Dictionaries.FIELDISNOTNULLBOO:Dictionaries.FIELDISNULLBOO);
                autoField.setFieldPrimary(autoField.getFieldPrimaryBoo()?Dictionaries.FIELDNOTPRIMARYBOO:Dictionaries.FIELDPRIMARYBOO);
                autoField.setFieldIsBeRelated(autoField.getFieldIsBeRelatedBoo()?Dictionaries.COMMONTRUE:Dictionaries.COMMONFALSE);
                if (!autoField.getFieldIsBeRelatedBoo()){
                    autoField.setFieldRelatedTableName("");
                    autoField.setFieldRelatedField("");
                    autoField.setFieldRelatedFieldShow("");
                }
            }
            QueryWrapper<SysAutoField> queryWrapper = new QueryWrapper<>();
            if(!isAdd){
                autoFieldService.remove(queryWrapper.eq("table_id", sysAutoTable.getId()));
            }
            autoFieldService.saveBatch(sysAutoTable.getAutoFieldList());
        }
    }

    /**
     * 创建表语句
     */
    public void creatTable(SysAutoTable sysAutoTable1){
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            StringBuffer sql = new StringBuffer();
            sql.append("CREATE TABLE ").append(sysAutoTable1.getTableName()).append(" (id varchar(36) NOT NULL COMMENT '主键'");
            for (SysAutoField autoField : sysAutoTable1.getAutoFieldList()) {
                if (!"id".equals(autoField.getFieldName())) {
                    sql.append("," + autoField.getFieldName() + " ");

                    if (!StringUtils.isBlank(autoField.getFieldDecimal()) && autoField.getFieldDecimal() != 0) {
                        sql.append(autoField.getFieldType() + "(" + autoField.getFieldLength() + "," + autoField.getFieldDecimal() + ") ");
                    } else {
                        sql.append(autoField.getFieldType() + "(" + autoField.getFieldLength() + ") ");
                    }

                    if ("1".equals(autoField.getFieldIsNull())) {
                        sql.append(" not ");
                    }
                    sql.append(" NULL COMMENT '" + autoField.getFieldDes() + "'");
                    if ("1".equals(autoField.getFieldPrimary())) {
                        sql.append(",PRIMARY KEY (`" + autoField.getFieldName() + "`) USING BTREE");
                    }
                }

            }
            sql.append(",PRIMARY KEY (`id`) USING BTREE)");
            stmt.execute(sql.toString());
            stmt.close();
            conn.close();
        }catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("表生成错误");
            }finally {
            try {
                if (null != stmt) {
                    stmt.close();
                }
                if (null != conn) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }
        }
    }

    /**
     * 改变数据
     */
    public SysAutoTable changeTableData(SysAutoTable sysAutoTable) {
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

        //删除子表 sys_auto_field
        autoFieldService.remove(new QueryWrapper<SysAutoField>().eq("table_id", id));
        //删除子表的子表 sys_auto_field_param
        SysAutoParam autoParam = paramService.getOne(new QueryWrapper<SysAutoParam>().eq("table_id", id));
        if (!StringUtils.isBlank(autoParam)){
            fieldParamService.remove(new QueryWrapper<SysAutoFieldParam>().eq("param_id", autoParam.getId()));
            //删除子表 sys_auto_param
            paramService.remove(new QueryWrapper<SysAutoParam>().eq("table_id", id));
        }
        //最后删除表，因为删除表无法回退
        SysAutoTable autoTable = getById(id);
        doMapper.deleteTable(autoTable.getTableName());
        //删除这条数据
        if (!removeById(id)){
            return new Result("500","", Dictionaries.DELETE_FAILED);
        }
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
        if (StringUtils.isBlank(sysAutoTable.getTableName())||StringUtils.isBlank(sysAutoTable.getTableType())){
            return Result.error("表名不能为空");
        }

        if (existboolean(sysAutoTable)){
            return Result.error("表已存在");

        }
        return Result.success("通过");
    }

    @Override
    public Result findByID(String id) {
        SysAutoTable autoTable = getById(id);
        List<SysAutoField> fields = autoFieldService.list(new QueryWrapper<SysAutoField>().eq("table_id", id).orderByAsc("field_index"));
        for (SysAutoField field : fields){
            field.setFieldIsNullBoo(Dictionaries.FIELDISNOTNULLBOO.equals(field.getFieldIsNull()));
            field.setFieldPrimaryBoo(Dictionaries.FIELDNOTPRIMARYBOO.equals(field.getFieldPrimary()));
        }
        autoTable.setAutoFieldList(fields);

        return Result.success(autoTable,"获取成功");
    }

    @Override
    public Result codeGeneration(ProjModel projModel) {
        //主备数据---主数据
        SysAutoTable autoTable= getById(projModel.getTableId());
        //其他数据
        projModel.setPackage_name(projModel.getPackageName().replace(".", "\\"));
        projModel.setTable_name(autoTable.getTableName());
        projModel.setTableName(StringUtils.underscoreToCamelCase(autoTable.getTableName()));
        projModel.set_TableName(StringUtils.upperFirstCase(projModel.getTableName()));
        //检测文件是否存在
        String targetPath = System.getProperty("user.dir")+"\\src\\main\\java\\"+projModel.getPackage_name()+"\\module\\"+projModel.getProjectName()+ "\\"+projModel.getTableName();
        File file = new File(targetPath);
        if (file.exists()){
            return Result.error("文件已存在，不能生成");
        }
        //主备数据---子数据一
        SysAutoParam autoParam = paramService.getOne(new QueryWrapper<SysAutoParam>().eq("table_id", projModel.getTableId()));
        //查询区域数据queryList
        List<SysAutoFieldParam> queryList = fieldParamService.findParamList(autoParam.getId(),Dictionaries.LAYOUTTYPEQUERY);
        for (SysAutoFieldParam param : queryList){
            if (!StringUtils.isBlank(param.getFieldName())){
                param.setFieldNameHump(StringUtils.underscoreToCamelCase(param.getFieldName()));
            }

        }
        autoParam.setQueryList(queryList);
        //表格区域数据tableList
        List<SysAutoFieldParam> tableList = fieldParamService.findParamList(autoParam.getId(),Dictionaries.LAYOUTTYPETABLE,Dictionaries.TABLEDEFAULT);
        for (SysAutoFieldParam param : tableList){
            if (!StringUtils.isBlank(param.getFieldName())){
                param.setFieldNameHump(StringUtils.underscoreToCamelCase(param.getFieldName()));
            }
        }
        autoParam.setTableList(tableList);
        //表格区域操作数据tableButtonList
        List<SysAutoFieldParam> tableButtonList = fieldParamService.findParamList(autoParam.getId(),Dictionaries.LAYOUTTYPETABLE,Dictionaries.TABLEOPERA);
        for (SysAutoFieldParam param : tableList){
            if (!StringUtils.isBlank(param.getFieldName())){
                param.setFieldNameHump(StringUtils.underscoreToCamelCase(param.getFieldName()));
            }
        }
        autoParam.setTableButtonList(tableButtonList);

        //编辑页form表单区域
        List<SysAutoFieldParam> editList = fieldParamService.findParamList(autoParam.getId(),Dictionaries.LAYOUTTYPEEDITFORM);
        for (SysAutoFieldParam param : editList){
            if (!StringUtils.isBlank(param.getFieldName())){
                param.setFieldNameHump(StringUtils.underscoreToCamelCase(param.getFieldName()));
            }
        }
        autoParam.setEditList(editList);

        autoTable.setAutoParam(autoParam);
        //主备数据---子数据二
//        List<SysAutoField> autoFieldList = autoFieldService.list(new QueryWrapper<SysAutoField>().eq("table_id", projModel.getTableId()));
        List<SysAutoField> autoFieldList = autoFieldService.getFieldList(projModel.getTableId());
        for (SysAutoField param : autoFieldList){
            param.setFieldNameHump(StringUtils.underscoreToCamelCase(param.getFieldName()));
            param.setFieldTypeToJava(CommonMap.javaTypeMap.get(param.getFieldType()));
        }
        autoTable.setAutoFieldList(autoFieldList);

        greatFile(autoTable,projModel,targetPath);
        return Result.success("生成成功");
    }

    private void greatFile(SysAutoTable autoTable,ProjModel projModel,String targetPath) {
        Map<String,Object> map = new HashMap();
        map.put("controller.java.vm","controller/" + projModel.get_TableName() + "Controller.java");
        map.put("service.java.vm","service/"+"I" + projModel.get_TableName() + "Service.java");
        map.put("serviceImpl.java.vm","service/impl/" + projModel.get_TableName() + "ServiceImpl.java");
        map.put("dao.java.vm","dao/" + projModel.get_TableName() + "Mapper.java");
        map.put("entity.java.vm","entity/" + projModel.get_TableName() + ".java");
        map.put("dao.xml.vm","dao/xml/" + projModel.get_TableName() + "Mapper.xml");
        map.put("query.vue.vm",projModel.getTableName() + "/query.vue");
        map.put("edit.vue.vm",projModel.getTableName() + "/edit.vue");
        for(String templateFile:map.keySet()){
            String targetFile = (String) map.get(templateFile);
            Properties pro = new Properties();
            //设置文本传入编码类型
            pro.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
            pro.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, System.getProperty("user.dir")+templateUrl+"one\\");
            VelocityEngine ve = new VelocityEngine(pro);
            VelocityContext context = new VelocityContext();

            context.put("projModel",projModel);
            context.put("autoTable",autoTable);
            context.put("autoFieldList",autoTable.getAutoFieldList());
            context.put("autoParam",autoTable.getAutoParam());
            context.put("queryList",autoTable.getAutoParam().getQueryList());
            context.put("tableList",autoTable.getAutoParam().getTableList());
            context.put("tableButtonList",autoTable.getAutoParam().getTableButtonList());
            context.put("editList",autoTable.getAutoParam().getEditList());

            Template t = ve.getTemplate(templateFile, "UTF-8");
            try {
                File file = new File(targetPath, targetFile);
                if (!file.getParentFile().exists())
                    file.getParentFile().mkdirs();
                if (!file.exists()){
                    file.createNewFile();
                }
                FileOutputStream outStream = null;
                outStream = new FileOutputStream(file);
                OutputStreamWriter writer = new OutputStreamWriter(outStream,"UTF-8");
                BufferedWriter sw = new BufferedWriter(writer);
                t.merge(context, sw);
                sw.flush();
                sw.close();
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("成功生成Java文件:" + (targetPath + targetFile).replaceAll("/", "\\\\"));
        }
    }

    /*
    *返回 true为数据库没有该表
    */
    private boolean existboolean(SysAutoTable sysAutoTable) {

        String name = doMapper.getDatabaseName();
        int tablecoount = doMapper.existable(sysAutoTable.getTableName(),name);
        return 0 != tablecoount;
    }
}
