package com.dhlg.module.blog.blogBlog.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlg.module.blog.blogBlog.entity.BlogBlog;
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
public interface BlogBlogMapper extends BaseMapper<BlogBlog> {

    IPage<BlogBlog> queryByCondition(Page page, @Param("parameter") Parameter parameter);

    IPage<BlogBlog> listFieldQuery(Page page, @Param("parameter") Map<String, Object> params);
}
        