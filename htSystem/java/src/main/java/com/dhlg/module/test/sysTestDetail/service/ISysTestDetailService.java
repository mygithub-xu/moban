package com.dhlg.module.test.sysTestDetail.service;

import com.dhlg.module.test.sysTestDetail.entity.SysTestDetail;
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
public interface ISysTestDetailService extends IService<SysTestDetail> {

    Result customSaveOrUpdate(SysTestDetail sysTestDetail);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);

    Result listFieldQuery(Map<String, Object> params);
}
