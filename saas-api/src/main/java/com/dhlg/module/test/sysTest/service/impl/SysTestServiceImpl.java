package com.dhlg.module.test.sysTest.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.test.sysTest.entity.SysTest;
import com.dhlg.module.test.sysTest.dao.SysTestMapper;
import com.dhlg.module.test.sysTest.service.ISysTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.module.test.tTestDemo.entity.TTestDemo;
import com.dhlg.module.test.tTestDemo.service.impl.TTestDemoServiceImpl;
import com.dhlg.utils.Dictionaries;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.common.ExcelUtils;
import com.dhlg.utils.common.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import com.dhlg.utils.GetLoginUser;
import com.dhlg.utils.common.DateUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
@Slf4j
@Service
public class SysTestServiceImpl extends ServiceImpl<SysTestMapper, SysTest> implements ISysTestService {

    @Autowired
    SysTestMapper doMapper;

    @Autowired
    TTestDemoServiceImpl testDemoService;

    @Resource(name="cacheUpdateTaskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;

    @Override
    public Result saveOrUpdateCommon(SysTest sysTest) {
        //判断新增还是修改
        if (!StringUtils.isBlank(sysTest.getId())) {
            //修改
            if(!updateById(sysTest)){

                return Result.error(Dictionaries.UPDATE_FAILED);
            }

            return Result.success(Dictionaries.SAVE_SUCCESS);
        }
        sysTest.setCreateTime(DateUtils.getCurrentDate());
        sysTest.setCreateUser(GetLoginUser.getCurrentUserId());
        sysTest.setId(StringUtils.uuid());
        if (!save(sysTest)){

            return Result.error(Dictionaries.SAVE_FAILED);
        }

        return Result.success(Dictionaries.SAVE_SUCCESS);
    }

    @Override
    public Result delete(String id) {
        if (!removeById(id)){
            return Result.error(Dictionaries.DELETE_FAILED);
        }
        return  Result.success(Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public Result query(QueryEntity<SysTest> parameter) {
        IPage<SysTest> dataList = doMapper.queryByCondition(parameter.getPage(), parameter.getCondition());

        return new Result(dataList);
    }
    @Async
    public void sayHello(String name) {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("***************************");
                System.out.println(Thread.currentThread().getName());
                System.out.println("***************************");
            }
        });
        System.out.println("***********threadTest***********");
    }
    @Override
    public Result query(String id) {
        return Result.success(getById(id));
    }

    @Override
    public List<SysTest> findAllAdta() {
        return list();
    }

    @Override
    public void down3(HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        List<TTestDemo> data = testDemoService.list();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        ExcelUtils.downSimple(response,data);
        long endTime2 = System.currentTimeMillis();
        System.out.println("程序运行时间2：" + (endTime - endTime2) + "ms");
    }

    @Override
    public Result deleteBatch(List<String> ids) {
        if (!removeByIds(ids)){

            return Result.error(Dictionaries.DELETE_FAILED);
        }
        return Result.success(Dictionaries.DELETE_SUCCESS);
    }
}
