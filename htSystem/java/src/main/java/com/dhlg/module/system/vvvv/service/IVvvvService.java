package com.dhlg.module.system.vvvv.service;

import com.dhlg.module.system.vvvv.entity.Vvvv;
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
public interface IVvvvService extends IService<Vvvv> {

    Result saveOrUpdateCommon(Vvvv vvvv);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result query(QueryEntity<Vvvv> parameter);

    Result query(String id);

}
