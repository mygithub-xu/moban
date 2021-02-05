package com.dhlg.module.system.sysAutoTem.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.system.sysAutoTem.entity.SysAutoTem;
import com.dhlg.module.system.sysAutoTem.dao.SysAutoTemMapper;
import com.dhlg.module.system.sysAutoTem.service.ISysAutoTemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.*;
import com.dhlg.utils.Parameter.Parameter;
import com.dhlg.utils.Parameter.QueryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SysAutoTemServiceImpl extends ServiceImpl<SysAutoTemMapper, SysAutoTem> implements ISysAutoTemService {

    @Autowired
    SysAutoTemMapper doMapper;

    @Override
    public Result saveOrUpdateCommon(SysAutoTem sysAutoTem) {
        //判断新增还是修改
        if (!StringUtils.isBlank(sysAutoTem.getId())) {
            //修改
            sysAutoTem.setCreateUser(GetLoginUser.getCurrentUserId());
            sysAutoTem.setUpdateTime(DateUtils.getCurrentDate());
            if(!updateById(sysAutoTem)){

                return Result.error(Dictionaries.UPDATE_FAILED);
            }

            return Result.success(Dictionaries.SAVE_SUCCESS);
        }
        sysAutoTem.setCreateTime(DateUtils.getCurrentDate());
        sysAutoTem.setCreateUser(GetLoginUser.getCurrentUserId());
        sysAutoTem.setId(StringUtils.uuid());
        if (!save(sysAutoTem)){

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
    public Result query(QueryEntity<SysAutoTem> parameter) {
        IPage<SysAutoTem> dataList = doMapper.queryByCondition(parameter.getPage(), parameter.getCondition());
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
