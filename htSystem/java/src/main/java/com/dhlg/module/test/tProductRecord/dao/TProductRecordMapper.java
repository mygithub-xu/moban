package com.dhlg.module.test.tProductRecord.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.test.tProductRecord.entity.TProductRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface TProductRecordMapper extends BaseMapper<TProductRecord> {

    IPage<TProductRecord> queryByCondition(Page page, @Param("params") TProductRecord parameter);

    IPage<TProductRecord> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        