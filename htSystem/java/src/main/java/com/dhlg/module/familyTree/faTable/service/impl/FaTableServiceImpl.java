package com.dhlg.module.familyTree.faTable.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.familyTree.faTable.entity.FaTable;
import com.dhlg.module.familyTree.faTable.dao.FaTableMapper;
import com.dhlg.module.familyTree.faTable.service.IFaTableService;
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
public class FaTableServiceImpl extends ServiceImpl<FaTableMapper, FaTable> implements IFaTableService {

    @Autowired
    FaTableMapper doMapper;

    @Override
    public Result saveOrUpdateCommon(FaTable faTable) {
        //判断新增还是修改
        if (!StringUtils.isBlank(faTable.getId())) {
            //修改
            faTable.setUpdateTime(DateUtils.getCurrentDate());
            faTable.setCreateUser(GetLoginUser.getCurrentUserId());
            if(!updateById(faTable)){

                return Result.error(Dictionaries.UPDATE_FAILED);
            }

            return Result.success(Dictionaries.SAVE_SUCCESS);
        }
        faTable.setCreateUser(GetLoginUser.getCurrentUserId());
        faTable.setCreateTime(DateUtils.getCurrentDate());
        faTable.setId(StringUtils.uuid());
        if (!save(faTable)){

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
    public Result query(QueryEntity<FaTable> parameter) {
        IPage<FaTable> dataList = doMapper.queryByCondition(parameter.getPage(), parameter.getCondition());
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
