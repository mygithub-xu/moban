package com.dhlg.module.system.sysAutoField.service;

import com.dhlg.module.system.sysAutoField.entity.SysAutoField;
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
public interface ISysAutoFieldService extends IService<SysAutoField> {

    Result customSaveOrUpdate(SysAutoField sysAutoField);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);

    Result listFieldQuery(Map<String, Object> params);
}
