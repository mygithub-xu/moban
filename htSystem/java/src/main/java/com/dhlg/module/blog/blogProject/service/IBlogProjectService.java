package com.dhlg.module.blog.blogProject.service;

import com.dhlg.module.blog.blogProject.entity.BlogProject;
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
public interface IBlogProjectService extends IService<BlogProject> {

    Result customSaveOrUpdate(BlogProject blogProject);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(Parameter parameter);

    Result listFieldQuery(Map<String, Object> params);
}
