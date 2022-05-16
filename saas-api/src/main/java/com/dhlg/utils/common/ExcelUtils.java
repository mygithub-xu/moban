package com.dhlg.utils.common;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.fastjson.JSON;
import com.dhlg.module.test.sysTest.entity.SysTest;
import org.apache.poi.ss.formula.functions.T;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    static String TEMPLATE_PATH = "src/main/resources/templates/excel/";

    public static void down(String templateFileName, HttpServletResponse response, Object... objs){
        templateFileName = TEMPLATE_PATH + templateFileName;

        setRespons(response);
        try {
            // 这里需要设置不关闭流
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(templateFileName).build();
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
            for (Object o : objs){
                excelWriter.fill(o, fillConfig, writeSheet);
            }
            excelWriter.finish();
        } catch (Exception e) {
            // 重置response
            errorReturn(response,e);
        }
    }

    public static<T> void downSimple(HttpServletResponse response, List<T> data){

        setRespons(response);
        try {
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), data.get(0).getClass()).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(data);
        } catch (Exception e) {
            // 重置response
            errorReturn(response,e);
        }
    }

    private static void errorReturn(HttpServletResponse response, Exception e) {
        response.reset();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        Map<String, String> map = new HashMap<>();
        map.put("status", "failure");
        map.put("message", "下载文件失败" + e.getMessage());
        try {
            response.getWriter().println(JSON.toJSONString(map));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static void setRespons(HttpServletResponse response) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = null;
        try {
            fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    }
}
