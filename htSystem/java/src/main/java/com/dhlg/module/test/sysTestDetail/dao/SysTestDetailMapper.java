package com.dhlg.module.test.sysTestDetail.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.test.sysTestDetail.entity.SysTestDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlg.common.utils.Parameter.Parameter;
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
public interface SysTestDetailMapper extends BaseMapper<SysTestDetail> {

    IPage<SysTestDetail> queryByCondition(Page page, @Param("parameter")Parameter parameter);

    IPage<SysTestDetail> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);

    void deleteByParentId(String parentId);
}
        