package com.dhlg.module.test.sysTest.service;

import com.dhlg.module.test.sysTest.entity.ExportSysTest;
import com.dhlg.module.test.sysTest.entity.SysTest;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.common.Parameter.Parameter;
import com.dhlg.utils.common.Parameter.Parameter2;
import com.dhlg.utils.common.Result;

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
public interface ISysTestService extends IService<SysTest> {

    Result customSaveOrUpdate(SysTest sysTest);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter2 parameter);

    Result listFieldQuery(Map<String, Object> params);

    Result getTreeData();

    List<ExportSysTest> findAllUser();
}
