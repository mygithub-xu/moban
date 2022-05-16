package com.dhlg.module.test.tSendMessageFile.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.test.tSendMessageFile.entity.TSendMessageFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface TSendMessageFileMapper extends BaseMapper<TSendMessageFile> {

    IPage<TSendMessageFile> queryByCondition(Page page, @Param("params") TSendMessageFile parameter);

    IPage<TSendMessageFile> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        