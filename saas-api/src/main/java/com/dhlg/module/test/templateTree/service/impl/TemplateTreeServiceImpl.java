package com.dhlg.module.test.templateTree.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhlg.module.test.templateTree.entity.TemplateTree;
import com.dhlg.module.test.templateTree.dao.TemplateTreeMapper;
import com.dhlg.module.test.templateTree.service.ITemplateTreeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.Dictionaries;
import com.dhlg.utils.Result;
import com.dhlg.utils.common.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
@Service
public class TemplateTreeServiceImpl extends ServiceImpl<TemplateTreeMapper, TemplateTree> implements ITemplateTreeService {

    @Autowired
    TemplateTreeMapper doMapper;

    @Override
    @Transactional
    public Result saveOrUpdateCommon(TemplateTree templateTree) {

        savetree(templateTree,null,0);
        //判断新增还是修改
        return Result.success(Dictionaries.SAVE_SUCCESS);
    }

    private void savetree(TemplateTree templateTree, String o,int dept) {
        templateTree.setRefParentId(o);
        templateTree.setNodeDepth(dept);
        if (!StringUtils.isBlank(templateTree.getMasterId())) {
            //修改
            updateById(templateTree);
        }
        // 新增
        templateTree.setMasterId(StringUtils.uuid());
        save(templateTree);
        // 保存子节点
        List<TemplateTree> templateTrees = templateTree.getChildren();
        if (!StringUtils.isBlank(templateTrees)){
            for (TemplateTree t : templateTrees){
                savetree(t,templateTree.getMasterId(),dept+1);
            }
        }
    }

    @Override
    public Result getTree() {
        List<TemplateTree> list = list();
        List<TemplateTree> aa = gentree(list,null);
        return Result.success(aa);
    }

    @Override
    public Result getTree2() {
        QueryWrapper<TemplateTree> queryWrapper = new QueryWrapper<>();
        // 找到根节点
        List<TemplateTree> list = list(queryWrapper.isNull("ref_parent_id"));
        List<TemplateTree> aa = gentree2(list);
        return Result.success(aa);
    }

    private List<TemplateTree> gentree2(List<TemplateTree> list) {
        for (TemplateTree t : list){
            QueryWrapper<TemplateTree> queryWrapper = new QueryWrapper<>();
            List<TemplateTree> chs = list(queryWrapper.eq("ref_parent_id", t.getMasterId()));
            gentree2(chs);
            t.setChildren(chs);
        }
        return list;
    }

    private List<TemplateTree> gentree(List<TemplateTree> list,String id) {
        List<TemplateTree> returnList = new ArrayList<>();
        if (StringUtils.isBlank(list)){
            return null;
        }
        // 获取tree结构数据
        for (TemplateTree t : list){
            if (t.getRefParentId()==null){
                List<TemplateTree> Childs = findChilds(list,t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    private List<TemplateTree> findChilds(List<TemplateTree> list, TemplateTree ba) {
        List<TemplateTree> retrunList = new ArrayList<>();
        for (TemplateTree t : list){
            if (ba.getMasterId().equals(t.getRefParentId())){
                findChilds(list,t);
                retrunList.add(t);
            }
        }
        // 装载子节点
        ba.setChildren(retrunList);
        return ba.getChildren();
    }
}
