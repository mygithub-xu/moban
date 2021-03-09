package com.dhlg.module.blog.blogBlog.service;

import com.dhlg.module.blog.blogBlog.entity.BlogBlog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.Parameter.Parameter;
import com.dhlg.utils.Result;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since
 */
public interface IBlogBlogService extends IService<BlogBlog> {

    Result customSaveOrUpdate(BlogBlog blogBlog);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);

    Result listFieldQuery(Map<String, Object> params);
}
