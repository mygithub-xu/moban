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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
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


}
