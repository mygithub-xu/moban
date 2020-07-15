package com.dhlg.module.system.sysLoginLog.controller;


import com.dhlg.module.system.sysLoginLog.entity.SysLoginLog;
import com.dhlg.module.system.sysLoginLog.service.ISysLoginLogService;
import com.dhlg.utils.common.Parameter;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.utils.common.exception.ParamIsNullException;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xu
 * @since 2020-02-22
 */
@RestController
@RequestMapping("/api/system/sysLoginLog")
@CrossOrigin
public class SysLoginLogController {

    @Autowired
    ISysLoginLogService loginLogService;

    @ApiOperation("根据条件分页获取日志数据")
    @PostMapping("/query")
    public Result query(@RequestBody Parameter parameter) {
        if (StringUtils.isBlank(parameter)) {
            throw new ParamIsNullException();
        }
        return loginLogService.queryByCondition(parameter);
    }


    @ApiOperation("批量删除日志")
    @PostMapping("/batchdelete")
    public Result batchDeleteLoginLog(@RequestBody List<String> ids) {

        return loginLogService.batchDeleteByIds(ids);

    }

//    @GetMapping("/excel")
//    public void getExcel(HttpServletResponse response) {
//        System.out.println("开始导出");
//        HSSFWorkbook wb = new HSSFWorkbook();
//        HSSFSheet sheet = wb.createSheet();
//        HSSFRow nRow = sheet.createRow(0);
//        HSSFCell nCell = nRow.createCell(0);
//        int rowNo = 0;
//        int colNo = 0;
////        设置excel第一行的标题
//        String[] title;
//        title = new String[]{"姓名", "性别", "年龄"};
//        nRow = sheet.createRow(rowNo++);
//        for (int i = 0; i < title.length; i++) {
//            nCell = nRow.createCell(i);
//            nCell.setCellValue(title[i]);
//        }
//        try {
//            /*
//             * 调用逻辑层函数查询
//             */
//            List<SysLoginLog> excelList = loginLogService.getExcelList();
//            //遍历并且创建行列
//            for (SysLoginLog dto : excelList) {
//                //控制列号
//                colNo = 0;
//                //每遍历一次创建一行
//                nRow = sheet.createRow(rowNo++);
//                //姓名
//                nCell = nRow.createCell(colNo++);
//                nCell.setCellValue(dto.getUserName());//姓名
//                //性别
//                nCell = nRow.createCell(colNo++);
//                nCell.setCellValue(dto.getLoginTime());
//                //年龄
//                nCell = nRow.createCell(colNo++);
//                nCell.setCellValue(dto.getLoginAddress());
//
//            }
//
////            返回到浏览器
//            ServletOutputStream outputStream = response.getOutputStream();
//            response.reset();
////            下列输出的xls名字可以自己修改（批量采购表样.xls）这个是例子`
//            response.setHeader("Content-Disposition","attchement;filename=" + new String("批量采购表样.xls".getBytes("gb2312"), "ISO8859-1"));
//            response.setContentType("application/msexcel");
//            wb.write(outputStream);
//            wb.close();
//            System.out.println("导出成功");
////            输出到本地
////            FileOutputStream fout = new FileOutputStream("E:/usrer.xls");
////            wb.write(fout);
////            fout.close();
//        } catch (Exception e) {
//            System.out.println("你异常了~~~快来看看我。。");
//        }
//}
    @GetMapping("/excel")
    public void getExcel(HttpServletResponse response) {


    }



}

