package com.dhlg.module.test.sysTest.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.test.sysTest.entity.SysTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface SysTestMapper extends BaseMapper<SysTest> {

    IPage<SysTest> queryByCondition(Page page, @Param("params") SysTest parameter);

    IPage<SysTest> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        