package com.dhlg.module.system.sysAutoTable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.common.utils.*;
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
import com.dhlg.common.utils.Parameter.Parameter;
import org.apache.tomcat.jni.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.dhlg.common.utils.mailUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.File;
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

    private static final String TO = "1967368657@qq.com";
    private static final String SUBJECT = "测试邮件";
    private static final String CONTENT = "test content";


    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    Connection conn = null;
    Statement stmt = null;

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

            if (!StringUtils.isBlank(sysAutoTable.getAutoFieldList())){
                for (SysAutoField autoField : sysAutoTable.getAutoFieldList()) {
                    autoField.setId(StringUtils.uuid());
                    autoField.setTableId(sysAutoTable.getId());
                    autoField.setFieldIsNull(autoField.getFieldIsNullBoo()?Dictionaries.FIELDISNOTNULLBOO:Dictionaries.FIELDISNULLBOO);
                    autoField.setFieldPrimary(autoField.getFieldPrimaryBoo()?Dictionaries.FIELDNOTPRIMARYBOO:Dictionaries.FIELDPRIMARYBOO);
                }
                autoFieldService.saveBatch(sysAutoTable.getAutoFieldList());
            }

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

        //保存明细
        if (!StringUtils.isBlank(sysAutoTable.getAutoFieldList())){
            for (SysAutoField autoField : sysAutoTable.getAutoFieldList()) {
                autoField.setId(StringUtils.uuid());
                autoField.setTableId(sysAutoTable.getId());
                autoField.setFieldIsNull(autoField.getFieldIsNullBoo()?Dictionaries.FIELDISNOTNULLBOO:Dictionaries.FIELDISNULLBOO);
                autoField.setFieldPrimary(autoField.getFieldPrimaryBoo()?Dictionaries.FIELDNOTPRIMARYBOO:Dictionaries.FIELDPRIMARYBOO);
            }
            QueryWrapper<SysAutoField> queryWrapper = new QueryWrapper<>();
            autoFieldService.remove(queryWrapper.eq("table_id", sysAutoTable.getId()));
            autoFieldService.saveBatch(sysAutoTable.getAutoFieldList());
        }
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
        SysAutoTable autoTable= getById(projModel.getTableId());
        //检测文件是否存在
        File file = new File(System.getProperty("user.dir")+projModel.getPackageName()+"\\module"+);
        return null;
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
