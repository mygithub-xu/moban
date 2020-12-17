package com.dhlg.module.system.sysTable.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.system.sysTable.entity.SysTable;
import com.dhlg.module.system.sysTable.dao.SysTableMapper;
import com.dhlg.module.system.sysTable.service.ISysTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.common.utils.Dictionaries;
import com.dhlg.common.utils.Parameter.Parameter;
import com.dhlg.common.utils.Result;
import com.dhlg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SysTableServiceImpl extends ServiceImpl<SysTableMapper, SysTable> implements ISysTableService {

    @Autowired
    SysTableMapper doMapper;

    @Override
    public Result customSaveOrUpdate(SysTable sysTable) {
        //判断新增还是修改
        if (!StringUtils.isBlank(sysTable.getId())) {
            //新增
            if(!updateById(sysTable)){
                return new Result("500","", Dictionaries.UPDATE_FAILED);
            }
            return new Result("200","",Dictionaries.UPDATE_SUCCESS);
        }

        if (!save(sysTable)){
            return new Result("500","", Dictionaries.SAVE_FAILED);
        }
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
        IPage<SysTable> dataList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(dataList);
    }

    @Override
    public Result listFieldQuery(Map<String, Object> params) {
        Long number = Long.valueOf(String.valueOf(params.getOrDefault("number", 1)));
        Long size = Long.valueOf(String.valueOf(params.getOrDefault("size", 10)));
        IPage<SysTable> dataList = doMapper.listFieldQuery(new Page(number, size), params);
        return new Result("200", dataList);
    }



}
