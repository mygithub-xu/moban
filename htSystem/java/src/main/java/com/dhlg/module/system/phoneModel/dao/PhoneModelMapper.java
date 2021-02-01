package com.dhlg.module.system.phoneModel.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.system.phoneModel.entity.PhoneModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface PhoneModelMapper extends BaseMapper<PhoneModel> {

    IPage<PhoneModel> queryByCondition(Page page, @Param("params") PhoneModel parameter);

    IPage<PhoneModel> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        