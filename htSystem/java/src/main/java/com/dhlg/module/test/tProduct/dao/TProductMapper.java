package com.dhlg.module.test.tProduct.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.test.tProduct.entity.TProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface TProductMapper extends BaseMapper<TProduct> {

    IPage<TProduct> queryByCondition(Page page, @Param("params") TProduct parameter);

    IPage<TProduct> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        