package com.dhlg.module.familyTree.faUser.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.familyTree.faUser.entity.FaUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface FaUserMapper extends BaseMapper<FaUser> {

    IPage<FaUser> queryByCondition(Page page, @Param("params") FaUser parameter);

    IPage<FaUser> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        