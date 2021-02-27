package com.dhlg.module.system.yyyy.service;

import com.dhlg.module.system.yyyy.entity.Yyyy;
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
public interface IYyyyService extends IService<Yyyy> {

    Result saveOrUpdateCommon(Yyyy yyyy);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result query(QueryEntity<Yyyy> parameter);

    Result query(String id);

}
