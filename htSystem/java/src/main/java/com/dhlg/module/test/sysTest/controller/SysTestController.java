package com.dhlg.module.test.sysTest.controller;

import com.dhlg.module.test.sysTest.entity.SysTest;
import com.dhlg.module.test.sysTest.service.ISysTestService;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.common.ExcelUtils;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.exception.ParamIsNullException;
import com.dhlg.utils.common.UserInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import javax.transaction.SystemException;
import javax.servlet.RequestDispatcher;
import javax.mail.MessagingException;
import javax.transaction.SystemException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xu
 * @since
 */

@RestController
@RequestMapping("/api/test/sysTest")
@CrossOrigin
public class SysTestController {

        @Autowired
        ISysTestService doService;

        @Autowired
        UserInfo userInfo;

        @ApiOperation("保存或者更新")
        @PostMapping("/saveOrUpdate")
        public Result saveOrUpdate(@RequestBody SysTest sysTest) {
                if(StringUtils.isBlank(sysTest)){
                        throw new ParamIsNullException();
                }
                return  doService.saveOrUpdateCommon(sysTest);
        }

        @ApiOperation("删除（物理删除）")
        @DeleteMapping("/deleteById/{id}")
        public Result delete(@PathVariable String id) {
                new Hashtable<>();
                new HashMap<>();
                return doService.delete(id);
        }

        @ApiOperation("查询")
        @PostMapping("/query")
        public Result query(@RequestBody QueryEntity<SysTest> parameter) {
                if(StringUtils.isBlank(parameter)){
                        throw new ParamIsNullException();
                }
                return  doService.query(parameter);
        }

        @ApiOperation("查询")
        @GetMapping("/queryById/{id}")
        public Result query(@PathVariable String id) {
                return  doService.query(id);
        }

        @ApiOperation("批量删除（物理删除）")
        @PostMapping("/batchDelete")
        public Result batchDelete(@RequestBody List<String> ids) {

                return doService.deleteBatch(ids);
        }

        @ApiOperation("下载")
        @PostMapping("/down")
        public void down(@RequestBody List<SysTest> data , HttpServletResponse response) {
                for(SysTest test : data){
                        String status =  "1".equals(test.getTestStatus()) ?"启用" : "禁用";
                        test.setTestStatus(status);
                }

                ExcelUtils.downSimple(response,data);
        }

        @ApiOperation("复杂导出模板")
        @PostMapping("/down2")
        public void down2(@RequestBody List<SysTest> data , HttpServletResponse response){
                for(SysTest test : data){
                        String status =  "1".equals(test.getTestStatus()) ?"启用" : "禁用";
                        test.setTestStatus(status);
                }
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("total", 1000);
                ExcelUtils.down("demo.xlsx",response,data,map);
        }

        @ApiOperation("下载")
        @PostMapping("/down3")
        public void down3(HttpServletResponse response) {
                doService.down3(response);
        }

}

