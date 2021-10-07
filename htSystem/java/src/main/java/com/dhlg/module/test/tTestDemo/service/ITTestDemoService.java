package com.dhlg.module.test.tTestDemo.service;

import com.dhlg.module.test.tTestDemo.entity.TTestDemo;
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
public interface ITTestDemoService extends IService<TTestDemo> {

    Result saveOrUpdateCommon(TTestDemo tTestDemo);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result query(QueryEntity<TTestDemo> parameter);

    Result query(String id);

}
