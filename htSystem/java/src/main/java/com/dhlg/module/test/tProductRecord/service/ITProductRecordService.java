package com.dhlg.module.test.tProductRecord.service;

import com.dhlg.module.test.tProductRecord.entity.TProductRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since
 */
public interface ITProductRecordService extends IService<TProductRecord> {

    Result saveOrUpdateCommon(TProductRecord tProductRecord);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result query(QueryEntity<TProductRecord> parameter);

    Result query(String id);

}
