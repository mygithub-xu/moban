package com.dhlg.module.test.tProductRecord.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.test.tProductRecord.entity.TProductRecord;
import com.dhlg.module.test.tProductRecord.dao.TProductRecordMapper;
import com.dhlg.module.test.tProductRecord.service.ITProductRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.Dictionaries;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.common.StringUtils;

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
public class TProductRecordServiceImpl extends ServiceImpl<TProductRecordMapper, TProductRecord> implements ITProductRecordService {

    @Autowired
    TProductRecordMapper doMapper;

    @Override
    public Result saveOrUpdateCommon(TProductRecord tProductRecord) {
        //判断新增还是修改
        if (!StringUtils.isBlank(tProductRecord.getId())) {
            //修改
            if(!updateById(tProductRecord)){

                return Result.error(Dictionaries.UPDATE_FAILED);
            }

            return Result.success(Dictionaries.SAVE_SUCCESS);
        }
        tProductRecord.setId(StringUtils.uuid());
        if (!save(tProductRecord)){

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
    public Result query(QueryEntity<TProductRecord> parameter) {
        IPage<TProductRecord> dataList = doMapper.queryByCondition(parameter.getPage(), parameter.getCondition());
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

    public void insertProductRecord(int userId) {
        TProductRecord record = new TProductRecord();
        record.setId(StringUtils.uuid());
        record.setProductNo("No111111");
        record.setUserId(userId);
        save(record);
    }
}
