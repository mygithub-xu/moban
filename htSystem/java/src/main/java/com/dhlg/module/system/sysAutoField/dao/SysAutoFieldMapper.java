package com.dhlg.module.system.sysAutoField.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.system.sysAutoField.entity.SysAutoField;
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
public interface SysAutoFieldMapper extends BaseMapper<SysAutoField> {

    IPage<SysAutoField> queryByCondition(Page page, @Param("parameter")Parameter parameter);

    IPage<SysAutoField> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        