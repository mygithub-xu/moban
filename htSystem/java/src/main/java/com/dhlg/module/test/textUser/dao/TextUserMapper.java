package com.dhlg.module.test.textUser.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.test.textUser.entity.TextUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlg.utils.common.Parameter;
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
public interface TextUserMapper extends BaseMapper<TextUser> {

    IPage<TextUser> queryByCondition(Page page, @Param("parameter")Parameter parameter);

    IPage<TextUser> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        