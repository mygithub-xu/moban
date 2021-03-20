package com.dhlg.module.familyTree.tableUser.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.familyTree.tableUser.entity.TableUser;
import com.dhlg.module.familyTree.tableUser.dao.TableUserMapper;
import com.dhlg.module.familyTree.tableUser.service.ITableUserService;
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
public class TableUserServiceImpl extends ServiceImpl<TableUserMapper, TableUser> implements ITableUserService {

    @Autowired
    TableUserMapper doMapper;

    @Override
    public Result saveOrUpdateCommon(TableUser tableUser) {
        //判断新增还是修改
        if (!StringUtils.isBlank(tableUser.getId())) {
            //修改
            if(!updateById(tableUser)){

                return Result.error(Dictionaries.UPDATE_FAILED);
            }

            return Result.success(Dictionaries.SAVE_SUCCESS);
        }
        tableUser.setId(StringUtils.uuid());
        if (!save(tableUser)){

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
    public Result query(QueryEntity<TableUser> parameter) {
        IPage<TableUser> dataList = doMapper.queryByCondition(parameter.getPage(), parameter.getCondition());
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
