package com.dhlg.module.system.sysAutoTem.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.system.sysAutoTem.entity.SysAutoTem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface SysAutoTemMapper extends BaseMapper<SysAutoTem> {

    IPage<SysAutoTem> queryByCondition(Page page, @Param("params") SysAutoTem parameter);

    IPage<SysAutoTem> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        