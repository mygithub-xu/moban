package com.dhlg.module.test.templateTree.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.test.templateTree.entity.TemplateTree;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface TemplateTreeMapper extends BaseMapper<TemplateTree> {

    IPage<TemplateTree> queryByCondition(Page page, @Param("params") TemplateTree parameter);

    IPage<TemplateTree> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        