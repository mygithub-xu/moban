package com.dhlg.module.test.sysTest.controller;

import com.dhlg.module.test.sysTest.entity.ExportSysTest;
import com.dhlg.module.test.sysTest.entity.SysTest;
import com.dhlg.module.test.sysTest.service.ISysTestService;

import com.dhlg.common.utils.DateUtils;
import com.dhlg.common.utils.Parameter.Parameter2;
import com.dhlg.common.utils.Result;
import com.dhlg.common.utils.StringUtils;
import com.dhlg.common.utils.exception.ParamIsNullException;
import com.dhlg.common.utils.uploadDown.ExcelUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
                return  doService.customSaveOrUpdate(sysTest);
        }


        @ApiOperation("删除")
        @DeleteMapping("/deleteById/{id}")
        public Result delete(@PathVariable String id) {

                return doService.delete(id);
        }

        @ApiOperation("批量删除")
        @PostMapping("/batchdelete")
        public Result batchDelete(@RequestBody List<String> ids) {

                return doService.deleteBatch(ids);
        }

        @ApiOperation("查询")
        @PostMapping("/querybycondition")
        public Result queryByCondition(@RequestBody Parameter2 parameter) {
                if(StringUtils.isBlank(parameter)){
                        throw new ParamIsNullException();
                }
                Result result=doService.queryByCondition(parameter);
                return  result;
        }

        @ApiOperation("列表字段查询")
        @PostMapping("/listFieldQuery")
        public Result listFieldQuery(@RequestBody Map<String, Object> params) {
                return  doService.listFieldQuery(params);
        }

        @ApiOperation("获取树状数据")
        @GetMapping("/getTreeData")
        public Result getTreeData() {
                return  doService.getTreeData();
        }


        @ApiOperation("导出")
        @GetMapping ("/exportExcel")
        public void  export(HttpServletResponse response)  {
                List<ExportSysTest> list=doService.findAllUser();
                ExportSysTest exportSysTest=new ExportSysTest();
                ExcelUtil.writeExcel(response,list,"测试xlsx","aaaa",exportSysTest);

//                ServletOutputStream out = null;
//                try {
//                        out = response.getOutputStream();
//
//                ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
//                String fileName = "测试exportExcel";
//                Sheet sheet = new Sheet(1, 0,ExportSysTest.class);
//                //设置自适应宽度
//                sheet.setAutoWidth(Boolean.TRUE);
//                // 第一个 sheet 名称
//                sheet.setSheetName("第一个sheet");
//                writer.write(list, sheet);
//                //通知浏览器以附件的形式下载处理，设置返回头要注意文件名有中文
//                response.setHeader("Content-disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" ) + ".xlsx");
//                writer.finish();
//                response.setContentType("multipart/form-data");
//                response.setCharacterEncoding("utf-8");
//                out.flush();
//                } catch (IOException e) {
//                        e.printStackTrace();
//                }
        }


        @ApiOperation("导入")
        @PostMapping("/importExcel")
        public Result import_order(@RequestParam("file")MultipartFile excel) {

                Object objList = ExcelUtil.readExcel(excel, new ExportSysTest(), 1, 1);

                if (objList == null) {
                        return new Result("500", "导入的数据不能为空","");
                }

                List<ExportSysTest> orderList = (List) objList;

                for (ExportSysTest test : orderList) {
                        System.out.println("aaaa");

                }

                List<SysTest> orderList2 = (List) objList;

                for (SysTest test : orderList2) {
                        test.setId(StringUtils.uuid());
                        test.setCreateTime(DateUtils.getCurrentDate());
                        System.out.println("bbbb");

                }

                if (orderList == null || orderList.size() <= 0) {
                        return new Result("500", "导入的数据不能为空","");
                }

//                orderList.forEach(System.out::println);

                return new Result("500", "导入的数据不能为空","");
        }


        }

