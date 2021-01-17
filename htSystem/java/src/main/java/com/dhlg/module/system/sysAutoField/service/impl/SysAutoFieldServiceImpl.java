package com.dhlg.module.system.sysAutoField.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.system.sysAutoField.entity.SysAutoField;
import com.dhlg.module.system.sysAutoField.dao.SysAutoFieldMapper;
import com.dhlg.module.system.sysAutoField.service.ISysAutoFieldService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.Dictionaries;
import com.dhlg.utils.Parameter.Parameter;
import com.dhlg.utils.Result;
import com.dhlg.utils.StringUtils;
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
public class SysAutoFieldServiceImpl extends ServiceImpl<SysAutoFieldMapper, SysAutoField> implements ISysAutoFieldService {

    @Autowired
    SysAutoFieldMapper doMapper;

    @Override
    public Result customSaveOrUpdate(SysAutoField sysAutoField) {
        //判断新增还是修改
        if (!StringUtils.isBlank(sysAutoField.getId())) {
            //新增
            if(!updateById(sysAutoField)){
                return new Result("500","", Dictionaries.UPDATE_FAILED);
            }
            return new Result("200","",Dictionaries.UPDATE_SUCCESS);
        }

        if (!save(sysAutoField)){
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
        IPage<SysAutoField> dataList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(dataList);
    }

    @Override
    public Result listFieldQuery(Map<String, Object> params) {
        Long number = Long.valueOf(String.valueOf(params.getOrDefault("number", 1)));
        Long size = Long.valueOf(String.valueOf(params.getOrDefault("size", 10)));
        IPage<SysAutoField> dataList = doMapper.listFieldQuery(new Page(number, size), params);
        return new Result("200", dataList);
    }

    @Override
    public Result findByTableID(String id) {
        QueryWrapper<SysAutoField> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("table_id",id).orderBy(true, true, "field_index");

        List<SysAutoField> list = list(queryWrapper);

        return Result.success(list,"获取成功");
    }
}
