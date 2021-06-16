package com.dhlg.module.test.sysTest.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.fastjson.JSON;
import com.dhlg.module.test.sysTest.entity.SysTest;
import com.dhlg.module.test.sysTest.service.ISysTestService;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.StringUtils;
import com.dhlg.exception.ParamIsNullException;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        public void down(@RequestBody List<SysTest> data , HttpServletResponse response) throws IOException {
                for(SysTest test : data){
                        String status =  "1".equals(test.getTestStatus()) ?"启用" : "禁用";
                        test.setTestStatus(status);
                }
                // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
                try {
//                        List<SysTest> allAdta = doService.findAllAdta();
                        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                        response.setCharacterEncoding("utf-8");
                        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
                        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
                        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
                        // 这里需要设置不关闭流
                        EasyExcel.write(response.getOutputStream(), SysTest.class).autoCloseStream(Boolean.FALSE).sheet("模板")
                                .doWrite(data);
                } catch (Exception e) {
                        // 重置response
                        response.reset();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("utf-8");
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("status", "failure");
                        map.put("message", "下载文件失败" + e.getMessage());
                        response.getWriter().println(JSON.toJSONString(map));
                }
        }
        @ApiOperation("复杂导出模板")
        @PostMapping("/down2")
        public void down2(@RequestBody List<SysTest> data , HttpServletResponse response) throws IOException{
                for(SysTest test : data){
                        String status =  "1".equals(test.getTestStatus()) ?"启用" : "禁用";
                        test.setTestStatus(status);
                }
                String TEMPLATE_PATH = "src/main/resources/templates/excel/";
                // 写法1
                // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
                String templateFileName = TEMPLATE_PATH + "demo.xlsx";
//                // 方案1 一下子全部放到内存里面 并填充
//                String fileName = "listFill" + System.currentTimeMillis() + ".xlsx";
//                // 这里 会填充到第一个sheet， 然后文件流会自动关闭
//                EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(data);
                try {
//                        List<SysTest> allAdta = doService.findAllAdta();
                        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                        response.setCharacterEncoding("utf-8");
                        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
                        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
                        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
                        // 这里需要设置不关闭流
                        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), SysTest.class).withTemplate(templateFileName).build();
                        WriteSheet writeSheet = EasyExcel.writerSheet().build();
                        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
                        excelWriter.fill(data, fillConfig, writeSheet);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("total", 1000);
                        excelWriter.fill(map, writeSheet);
                        excelWriter.finish();
                } catch (Exception e) {
                        // 重置response
                        response.reset();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("utf-8");
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("status", "failure");
                        map.put("message", "下载文件失败" + e.getMessage());
                        response.getWriter().println(JSON.toJSONString(map));
                }
        }

        @GetMapping("/read")
        public void read() {
                // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
                // 写法1：
                String fileName =  "demo" + File.separator + "demo.xlsx";
                // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
                EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
        }
}

