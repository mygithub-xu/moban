package com.dhlg.module.system.yyyy.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.system.yyyy.entity.Yyyy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface YyyyMapper extends BaseMapper<Yyyy> {

    IPage<Yyyy> queryByCondition(Page page, @Param("params") Yyyy parameter);

    IPage<Yyyy> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        