package com.dhlg.module.system.sysCodeAuto.service;

import com.dhlg.module.system.sysCodeAuto.entity.SysCodeAuto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.common.utils.Parameter.Parameter;
import com.dhlg.common.utils.Result;

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
public interface ISysCodeAutoService extends IService<SysCodeAuto> {

    Result customSaveOrUpdate(SysCodeAuto sysCodeAuto);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);

    Result listFieldQuery(Map<String, Object> params);
}
