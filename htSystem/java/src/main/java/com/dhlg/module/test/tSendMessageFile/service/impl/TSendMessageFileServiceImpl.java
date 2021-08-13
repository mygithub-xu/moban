package com.dhlg.module.test.tSendMessageFile.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.test.tSendMessageFile.entity.TSendMessageFile;
import com.dhlg.module.test.tSendMessageFile.dao.TSendMessageFileMapper;
import com.dhlg.module.test.tSendMessageFile.service.ITSendMessageFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.Dictionaries;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.common.FileUtils;
import com.dhlg.utils.common.HttpUtils;
import com.dhlg.utils.common.StringUtils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dhlg.utils.GetLoginUser;
import com.dhlg.utils.common.DateUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
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
@Slf4j
@Service
public class TSendMessageFileServiceImpl extends ServiceImpl<TSendMessageFileMapper, TSendMessageFile> implements ITSendMessageFileService {

    @Autowired
    TSendMessageFileMapper doMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void saveOrUpdateCommon(TSendMessageFile tSendMessageFile,HttpServletResponse response) {
        // 生成file
//        createFile(tSendMessageFile);
        createZip();
        // 插入数据库
//        saveInData(tSendMessageFile);
    }

    public void createZip() {
        String fileSrc = "D:/aaaaaa";
        String storeSrc = "D:/bbbb";
        String zipName = "mytest04.zip";
        FileUtils.compressToZip(fileSrc,storeSrc,zipName);
        File file = new File(storeSrc, zipName);
        FileUtils.downFile(file);
    }

    public void createFile() {
        String fileName =  "D:/aaaaaa/" + System.currentTimeMillis() + ".xlsx";
        String TEMPLATE_PATH = "src/main/resources/templates/excel/cpt/";
        String templateFileName = TEMPLATE_PATH + "testDemo.xlsx";
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("demandNumber", "00000000");
        map.put("demandName", 5.2);
        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(map);
    }

    public void saveInData(TSendMessageFile tSendMessageFile){
        //判断新增还是修改
        if (!StringUtils.isBlank(tSendMessageFile.getId())) {
            //修改
            tSendMessageFile.setUpdateTime(DateUtils.getCurrentDate());
            tSendMessageFile.setCreateUser(GetLoginUser.getCurrentUserId());
            if(!updateById(tSendMessageFile)){
                throw new RuntimeException("更新sql失败");
            }
        }
        tSendMessageFile.setCreateUser(GetLoginUser.getCurrentUserId());
        tSendMessageFile.setCreateTime(DateUtils.getCurrentDate());
        tSendMessageFile.setId(StringUtils.uuid());
        if (!save(tSendMessageFile)){
            throw new RuntimeException("更新sql失败");
        }
    }

    @Override
    public Result delete(String id) {
        if (!removeById(id)){
            return Result.error(Dictionaries.DELETE_FAILED);
        }
        return  Result.success(Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public Result query(QueryEntity<TSendMessageFile> parameter) {
        IPage<TSendMessageFile> dataList = doMapper.queryByCondition(parameter.getPage(), parameter.getCondition());
        return new Result(dataList);
    }

    @Override
    public Result query(String id) {
        return Result.success(getById(id));
    }

    @Override
    public Result getVersion() {
        rabbitTemplate.convertAndSend("test.direct","test","aaaaaa哈哈哈哈或");

        String url = "https://uattest.life.cntaiping.com:8443/mobile/applicationMarket";
        String param = "sAction=queryAppList&sType=2,4&appCate=0";
        String result = HttpUtils.doGet(url, param);
        JSONObject resultJson = JSONObject.parseObject(result);
        JSONArray jsonArray= JSONArray.parseArray(resultJson.getString("products").toString());
        HashMap<String, String> resultMap = new HashMap<>();
        for(int i=0;i<jsonArray.size();i++){
            JSONObject object= (JSONObject) jsonArray.get(i);
            if ("太平产品通Android".equals(object.get("appAbbr"))){
                resultMap.put("android", String.valueOf(object.get("releaseCode")));
            }
            if ("太平产品通IPhone".equals(object.get("appAbbr"))){
                resultMap.put("ios", String.valueOf(object.get("releaseCode")));
            }
        }
        return Result.success(resultMap);
    }

    @Override
    public Result deleteBatch(List<String> ids) {
        if (!removeByIds(ids)){

            return Result.error(Dictionaries.DELETE_FAILED);
        }
        return Result.success(Dictionaries.DELETE_SUCCESS);
    }
}
