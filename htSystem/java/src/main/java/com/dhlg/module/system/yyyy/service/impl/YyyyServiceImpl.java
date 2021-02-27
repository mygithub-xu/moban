package com.dhlg.module.system.yyyy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.system.yyyy.entity.Yyyy;
import com.dhlg.module.system.yyyy.dao.YyyyMapper;
import com.dhlg.module.system.yyyy.service.IYyyyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.Dictionaries;
import com.dhlg.utils.Parameter.Parameter;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dhlg.utils.GetLoginUser;
import com.dhlg.utils.DateUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
@Service
public class YyyyServiceImpl extends ServiceImpl<YyyyMapper, Yyyy> implements IYyyyService {

    @Autowired
    YyyyMapper doMapper;

    @Override
    public Result saveOrUpdateCommon(Yyyy yyyy) {
        //判断新增还是修改
        if (!StringUtils.isBlank(yyyy.getId())) {
            //修改
            yyyy.setCreateUser(GetLoginUser.getCurrentUserId());
            yyyy.setUpdateTime(DateUtils.getCurrentDate());
            if(!updateById(yyyy)){

                return Result.error(Dictionaries.UPDATE_FAILED);
            }

            return Result.success(Dictionaries.SAVE_SUCCESS);
        }
        yyyy.setCreateTime(DateUtils.getCurrentDate());
        yyyy.setCreateUser(GetLoginUser.getCurrentUserId());
        yyyy.setId(StringUtils.uuid());
        if (!save(yyyy)){

            return Result.error(Dictionaries.SAVE_FAILED);
        }

        return Result.success(Dictionaries.SAVE_SUCCESS);
    }

    @Override
    public Result delete(String id) {
        if (!removeById(id)){
            return Result.error(Dictionaries.DELETE_FAILED);
        }
        return  Result.success(Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public Result query(QueryEntity<Yyyy> parameter) {
        IPage<Yyyy> dataList = doMapper.queryByCondition(parameter.getPage(), parameter.getCondition());
        return new Result(dataList);
    }

    @Override
    public Result query(String id) {
        return Result.success(getById(id));
    }

    @Override
    public Result deleteBatch(List<String> ids) {
        if (!removeByIds(ids)){

            return Result.error(Dictionaries.DELETE_FAILED);
        }
        return Result.success(Dictionaries.DELETE_SUCCESS);
    }
}
