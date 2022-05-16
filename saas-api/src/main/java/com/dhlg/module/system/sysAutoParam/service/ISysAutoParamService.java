package com.dhlg.module.system.sysAutoParam.service;

import com.dhlg.module.system.sysAutoParam.entity.SysAutoParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.Parameter.Parameter;
import com.dhlg.utils.Result;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since
 */
public interface ISysAutoParamService extends IService<SysAutoParam> {

    Result customSaveOrUpdate(SysAutoParam sysAutoParam);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);

    Result listFieldQuery(Map<String, Object> params);

    Result findByTableID(String id);
}
