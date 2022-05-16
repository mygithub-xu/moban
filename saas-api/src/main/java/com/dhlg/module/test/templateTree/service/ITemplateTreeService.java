package com.dhlg.module.test.templateTree.service;

import com.dhlg.module.test.templateTree.entity.TemplateTree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since
 */
public interface ITemplateTreeService extends IService<TemplateTree> {

    Result saveOrUpdateCommon(TemplateTree templateTree);

    Result getTree();

    Result getTree2();
}
