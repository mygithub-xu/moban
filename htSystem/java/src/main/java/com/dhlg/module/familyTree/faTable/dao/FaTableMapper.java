package com.dhlg.module.familyTree.faTable.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.familyTree.faTable.entity.FaTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface FaTableMapper extends BaseMapper<FaTable> {

    IPage<FaTable> queryByCondition(Page page, @Param("params") FaTable parameter);

    IPage<FaTable> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        