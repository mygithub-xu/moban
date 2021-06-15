package com.dhlg.module.test.sysTest.service;

import com.dhlg.module.test.sysTest.entity.SysTest;
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
public interface ISysTestService extends IService<SysTest> {

    Result saveOrUpdateCommon(SysTest sysTest);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result query(QueryEntity<SysTest> parameter);

    Result query(String id);

    List<SysTest> findAllAdta();
}
