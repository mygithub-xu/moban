package com.dhlg.module.familyTree.tableUser.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.familyTree.tableUser.entity.TableUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface TableUserMapper extends BaseMapper<TableUser> {

    IPage<TableUser> queryByCondition(Page page, @Param("params") TableUser parameter);

    IPage<TableUser> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        