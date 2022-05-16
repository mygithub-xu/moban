package com.dhlg.module.system.sysAutoParam.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.system.sysAutoParam.entity.SysAutoParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlg.utils.Parameter.Parameter;
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
public interface SysAutoParamMapper extends BaseMapper<SysAutoParam> {

    IPage<SysAutoParam> queryByCondition(Page page, @Param("parameter")Parameter parameter);

    IPage<SysAutoParam> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        