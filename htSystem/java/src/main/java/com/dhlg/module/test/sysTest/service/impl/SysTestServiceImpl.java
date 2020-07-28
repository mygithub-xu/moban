package com.dhlg.module.test.sysTest.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.test.sysTest.entity.ExportSysTest;
import com.dhlg.module.test.sysTest.entity.SysTest;
import com.dhlg.module.test.sysTest.dao.SysTestMapper;
import com.dhlg.module.test.sysTest.service.ISysTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.module.test.sysTestDetail.entity.SysTestDetail;
import com.dhlg.module.test.sysTestDetail.service.impl.SysTestDetailServiceImpl;
import com.dhlg.utils.common.*;
import com.dhlg.utils.common.Parameter.Parameter;
import com.dhlg.utils.common.Parameter.Parameter2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
@Service
public class SysTestServiceImpl extends ServiceImpl<SysTestMapper, SysTest> implements ISysTestService {

    @Autowired
    SysTestMapper doMapper;

    @Autowired
    SysTestDetailServiceImpl testDetailService;

    @Override
    @Transactional
    public Result customSaveOrUpdate(SysTest sysTest) {
        //判断新增还是修改
        if (!StringUtils.isBlank(sysTest.getId())) {
            //新增
            if(!updateById(sysTest)){
                return new Result("500","", Dictionaries.UPDATE_FAILED);
            }

            //构造明细表
            if (!StringUtils.isBlank(sysTest.getTestSysTetailList())){
                for (SysTestDetail testDetail:sysTest.getTestSysTetailList()){
                    testDetail.setId(StringUtils.uuid());
                    testDetail.setParentId(sysTest.getId());
                }

                //删除其他明细表
                testDetailService.deleteByParentId(sysTest.getId());
                //保存现在的明细表
                testDetailService.saveBatch(sysTest.getTestSysTetailList());
            }



            return new Result("200","",Dictionaries.UPDATE_SUCCESS);
        }

        sysTest.setId(StringUtils.uuid());
        sysTest.setCreateUser(GetLoginUser.getCurrentUserId());
        if (!save(sysTest)){
            return new Result("500","", Dictionaries.SAVE_FAILED);
        }
        //构造明细表
        if (!StringUtils.isBlank(sysTest.getTestSysTetailList())){
            for (SysTestDetail testDetail:sysTest.getTestSysTetailList()){
                testDetail.setId(StringUtils.uuid());
                testDetail.setParentId(sysTest.getId());
            }
            //保存现在的明细表
            testDetailService.saveBatch(sysTest.getTestSysTetailList());
        }

        return new Result("200","",Dictionaries.SAVE_SUCCESS);
    }

    @Override
    public Result delete(String id) {
        if (!removeById(id)){
            return new Result("500","", Dictionaries.DELETE_FAILED);
        }
        return new Result("200","",Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public Result deleteBatch(List<String> ids) {
        if (!removeByIds(ids)){
            return new Result("500","", Dictionaries.DELETE_FAILED);
        }
        return new Result("200","",Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public Result queryByCondition(Parameter2 parameter) {
        parameter.setDefault();
        IPage<SysTest> dataList = doMapper.queryByCondition(parameter.getPage(), parameter.getCondition());
        return new Result(dataList);
    }

    @Override
    public Result listFieldQuery(Map<String, Object> params) {
        Long number = Long.valueOf(String.valueOf(params.getOrDefault("number", 1)));
        Long size = Long.valueOf(String.valueOf(params.getOrDefault("size", 10)));
        IPage<SysTest> dataList = doMapper.listFieldQuery(new Page(number, size), params);
        return new Result("200", dataList);
    }

    @Override
    public Result getTreeData() {
        List<SysTest> list = super.list();
        List<SysTest> rootNodes = InitTree.getRootNodes(list);

        return new Result("200",rootNodes);
    }

    @Override
    public List<ExportSysTest> findAllUser() {
        return doMapper.exportgetList();
    }
}
