package com.dhlg.module.test.sysTest.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.test.sysTest.entity.SysTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlg.utils.common.Parameter.Parameter;
import com.dhlg.utils.common.Parameter.Parameter2;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* <p>
*  Mapper 接口
* </p>
*
* @author xu
* @since
*/
public interface SysTestMapper extends BaseMapper<SysTest> {

    IPage<SysTest> queryByCondition(Page page, @Param("parameter") Map<String, Object> parameter);

    IPage<SysTest> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        