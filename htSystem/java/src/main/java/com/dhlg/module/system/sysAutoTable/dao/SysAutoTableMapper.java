package com.dhlg.module.system.sysAutoTable.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.system.sysAutoTable.entity.SysAutoTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface SysAutoTableMapper extends BaseMapper<SysAutoTable> {

    IPage<SysAutoTable> queryByCondition(Page page, @Param("params") SysAutoTable params);

    IPage<SysAutoTable> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);

    int existable(String tableName, String name);

    String getDatabaseName();

    void deleteTable(@Param("tableName")String tableName);
}
        