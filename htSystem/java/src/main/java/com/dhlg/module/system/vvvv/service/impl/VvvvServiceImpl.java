package com.dhlg.module.system.vvvv.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.system.vvvv.entity.Vvvv;
import com.dhlg.module.system.vvvv.dao.VvvvMapper;
import com.dhlg.module.system.vvvv.service.IVvvvService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.Dictionaries;
import com.dhlg.utils.Parameter.Parameter;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.StringUtils;
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
public class VvvvServiceImpl extends ServiceImpl<VvvvMapper, Vvvv> implements IVvvvService {

    @Autowired
    VvvvMapper doMapper;

    @Override
    public Result saveOrUpdateCommon(Vvvv vvvv) {
        //判断新增还是修改
        if (!StringUtils.isBlank(vvvv.getId())) {
            //修改
            if(!updateById(vvvv)){

                return Result.error(Dictionaries.UPDATE_FAILED);
            }

            return Result.success(Dictionaries.SAVE_SUCCESS);
        }
        vvvv.setId(StringUtils.uuid());
        if (!save(vvvv)){

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
    public Result query(QueryEntity<Vvvv> parameter) {
        IPage<Vvvv> dataList = doMapper.queryByCondition(parameter.getPage(), parameter.getCondition());
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
