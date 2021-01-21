package com.dhlg.module.system.vvvv.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.system.vvvv.entity.Vvvv;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface VvvvMapper extends BaseMapper<Vvvv> {

    IPage<Vvvv> queryByCondition(Page page, @Param("params") Vvvv parameter);

    IPage<Vvvv> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        