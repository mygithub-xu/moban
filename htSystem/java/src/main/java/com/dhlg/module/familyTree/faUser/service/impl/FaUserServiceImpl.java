package com.dhlg.module.familyTree.faUser.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.familyTree.faUser.entity.FaUser;
import com.dhlg.module.familyTree.faUser.dao.FaUserMapper;
import com.dhlg.module.familyTree.faUser.service.IFaUserService;
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
public class FaUserServiceImpl extends ServiceImpl<FaUserMapper, FaUser> implements IFaUserService {

    @Autowired
    FaUserMapper doMapper;

    @Override
    public Result saveOrUpdateCommon(FaUser faUser) {
        //判断新增还是修改
        if (!StringUtils.isBlank(faUser.getId())) {
            //修改
            faUser.setUpdateTime(DateUtils.getCurrentDate());
            faUser.setCreateUser(GetLoginUser.getCurrentUserId());
            if(!updateById(faUser)){

                return Result.error(Dictionaries.UPDATE_FAILED);
            }

            return Result.success(Dictionaries.SAVE_SUCCESS);
        }
        faUser.setCreateUser(GetLoginUser.getCurrentUserId());
        faUser.setCreateTime(DateUtils.getCurrentDate());
        faUser.setId(StringUtils.uuid());
        if (!save(faUser)){

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
    public Result query(QueryEntity<FaUser> parameter) {
        IPage<FaUser> dataList = doMapper.queryByCondition(parameter.getPage(), parameter.getCondition());
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
