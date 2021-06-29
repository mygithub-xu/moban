package com.dhlg.module.system.sysVerificationCode.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.system.sysVerificationCode.entity.SysVerificationCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface SysVerificationCodeMapper extends BaseMapper<SysVerificationCode> {

    IPage<SysVerificationCode> queryByCondition(Page page, @Param("params") SysVerificationCode parameter);

    IPage<SysVerificationCode> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        