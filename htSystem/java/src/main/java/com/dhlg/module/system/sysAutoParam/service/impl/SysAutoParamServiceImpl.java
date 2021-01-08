package com.dhlg.module.system.sysAutoParam.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.common.utils.*;
import com.dhlg.module.system.sysAutoFieldParam.entity.SysAutoFieldParam;
import com.dhlg.module.system.sysAutoFieldParam.service.impl.SysAutoFieldParamServiceImpl;
import com.dhlg.module.system.sysAutoParam.entity.SysAutoParam;
import com.dhlg.module.system.sysAutoParam.dao.SysAutoParamMapper;
import com.dhlg.module.system.sysAutoParam.service.ISysAutoParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.common.utils.Parameter.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class SysAutoParamServiceImpl extends ServiceImpl<SysAutoParamMapper, SysAutoParam> implements ISysAutoParamService {

    @Autowired
    SysAutoParamMapper doMapper;

    @Autowired
    SysAutoFieldParamServiceImpl fieldParamService;

    @Override
    @Transactional
    public Result customSaveOrUpdate(SysAutoParam sysAutoParam) {
        //判断新增还是修改
        if (!StringUtils.isBlank(sysAutoParam.getId())) {
            //修改
            updateById(sysAutoParam);
            if(!updateById(sysAutoParam)){
                return new Result("500","", Dictionaries.UPDATE_FAILED);
            }
            return new Result("200","",Dictionaries.UPDATE_SUCCESS);
        }

        //新增
        sysAutoParam.setId(StringUtils.uuid());
        sysAutoParam.setCreateUser(GetLoginUser.getCurrentUserId());
        sysAutoParam.setCreateTime(DateUtils.getCurrentDate());
        //是否展示查询区域
        if (sysAutoParam.getIsShowQuery()){
            sysAutoParam.setShowQuery("1");
        }
        //是否展示表格区域
        if (sysAutoParam.getIsShowTableData()){
            sysAutoParam.setShowTableData("1");
        }
        //是否显示分页区域
        if (sysAutoParam.getIsShowPage()){
            sysAutoParam.setShowPage("1");
        }
        if (!save(sysAutoParam)){
            return new Result("500","", Dictionaries.SAVE_FAILED);
        }

        //新增明细
        List<SysAutoFieldParam> sysAutoFieldParam = new ArrayList<>();
        //添加查询区域元素
        if (sysAutoParam.getIsShowQuery()){
            for (SysAutoFieldParam item : sysAutoParam.getQueryList()){
                item.setId(StringUtils.uuid());
                item.setParamId(sysAutoParam.getId());
                item.setLayoutType(Dictionaries.LAYOUTTYPEQUERY);
                sysAutoFieldParam.add(item);
            }
        }
        //添加表格区域元素
        if (sysAutoParam.getIsShowTableData()){
            for (SysAutoFieldParam item : sysAutoParam.getTableList()){
                item.setId(StringUtils.uuid());
                item.setParamId(sysAutoParam.getId());
                item.setLayoutType(Dictionaries.LAYOUTTYPETABLE);
                sysAutoFieldParam.add(item);
            }
        }
        fieldParamService.saveBatch(sysAutoFieldParam);

        return new Result("200","",Dictionaries.SAVE_SUCCESS);
    }

    @Override
    public Result delete(String id) {
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
        IPage<SysAutoParam> dataList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(dataList);
    }

    @Override
    public Result listFieldQuery(Map<String, Object> params) {
        Long number = Long.valueOf(String.valueOf(params.getOrDefault("number", 1)));
        Long size = Long.valueOf(String.valueOf(params.getOrDefault("size", 10)));
        IPage<SysAutoParam> dataList = doMapper.listFieldQuery(new Page(number, size), params);
        return new Result("200", dataList);
    }
}
