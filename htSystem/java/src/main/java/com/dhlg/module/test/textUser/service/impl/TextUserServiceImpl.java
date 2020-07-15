package com.dhlg.module.test.textUser.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.test.textUser.entity.TextUser;
import com.dhlg.module.test.textUser.dao.TextUserMapper;
import com.dhlg.module.test.textUser.service.ITextUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.common.Dictionaries;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.StringUtils;
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
public class TextUserServiceImpl extends ServiceImpl<TextUserMapper, TextUser> implements ITextUserService {

    @Autowired
    TextUserMapper doMapper;

    @Override
    public Result customSaveOrUpdate(TextUser textUser) {
        //判断新增还是修改
        if (!StringUtils.isBlank(textUser.getId())) {
            //新增
            if(!updateById(textUser)){
                return new Result("500","", Dictionaries.UPDATE_FAILED);
            }
            return new Result("200","",Dictionaries.UPDATE_SUCCESS);
        }

        if (!save(textUser)){
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
        IPage<TextUser> dataList = doMapper.queryByCondition(parameter.getPage(), parameter);
        return new Result(dataList);
    }

    @Override
    public Result listFieldQuery(Map<String, Object> params) {
        Long number = Long.valueOf(String.valueOf(params.getOrDefault("number", 1)));
        Long size = Long.valueOf(String.valueOf(params.getOrDefault("size", 10)));
        IPage<TextUser> dataList = doMapper.listFieldQuery(new Page(number, size), params);
        return new Result("200", dataList);
    }
}
