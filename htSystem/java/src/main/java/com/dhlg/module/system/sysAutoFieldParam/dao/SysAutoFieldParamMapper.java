package com.dhlg.module.system.sysAutoFieldParam.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.system.sysAutoFieldParam.entity.SysAutoFieldParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlg.utils.Parameter.Parameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* <p>
*  Mapper 接口
* </p>
*
* @author xu
* @since
*/
public interface SysAutoFieldParamMapper extends BaseMapper<SysAutoFieldParam> {

    IPage<SysAutoFieldParam> queryByCondition(Page page, @Param("parameter")Parameter parameter);

    IPage<SysAutoFieldParam> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);

    List<SysAutoFieldParam> findParamList(String id, String layoutType);
}
        