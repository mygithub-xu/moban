package com.dhlg.module.system.sysAutoTem.service;

import com.dhlg.module.system.sysAutoTem.entity.SysAutoTem;
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
public interface ISysAutoTemService extends IService<SysAutoTem> {

    Result saveOrUpdateCommon(SysAutoTem sysAutoTem);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result query(QueryEntity<SysAutoTem> parameter);

    Result query(String id);

}
