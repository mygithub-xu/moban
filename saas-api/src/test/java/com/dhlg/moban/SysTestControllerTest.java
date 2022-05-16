package com.dhlg.moban;
import com.dhlg.MobanApplication;
import com.dhlg.module.test.sysTest.service.ISysTestService;
import com.dhlg.module.test.tTestDemo.entity.TTestDemo;
import com.dhlg.module.test.tTestDemo.service.ITTestDemoService;
import com.dhlg.utils.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SysTestControllerTest {

    @Resource
    private ITTestDemoService doService;
    @Test
    public void testAdd(){
        Result query = doService.query("1");
        TTestDemo tTestDemo = new TTestDemo();
        tTestDemo.setAge(12);
        tTestDemo.setHobby("10");
        tTestDemo.setId("1");
        tTestDemo.setName("小明");
        Assert.assertEquals(Result.success(tTestDemo),query);
    }
}
