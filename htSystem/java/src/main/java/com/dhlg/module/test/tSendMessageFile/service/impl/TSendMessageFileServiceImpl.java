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

    String TEMPLATE_PATH = "src/main/resources/templates/excel/cpt/";
    String TEMPLATE_DIR = "\\src\\main\\resources\\templates\\";
    String SOURCE_PATH = System.getProperty("user.dir")+TEMPLATE_DIR;


    @Override
    public void saveOrUpdateCommon(TSendMessageFile tSendMessageFile,HttpServletResponse response) {
        // 生成file
//        createFile(tSendMessageFile);
        tSendMessageFile.setCreateTime(DateUtils.getCurrentDate());
        createFile(tSendMessageFile);
        // 插入数据库
//        saveInData(tSendMessageFile);
    }

    public void createZip(String path,String name) {
        String zipName = name+".zip";
        FileUtils.compressToZip(path,SOURCE_PATH,zipName);
        File file = new File(SOURCE_PATH, zipName);
        FileUtils.downFile(file);
//        FileUtils.delZipFile(zipName);
    }

    public void createFile(TSendMessageFile tSendMessageFile) {
        String path = getPath(tSendMessageFile);
        String name = getName(tSendMessageFile);;
        FileUtils.creatFile(path);
        // 生成测试用例
        creatDemoFile(tSendMessageFile,path,"-测试用例","testDemo");
        // 生成android
        creatDemoFile(tSendMessageFile,path,"-android","versionAndroid");
        // 生成ios
        creatDemoFile(tSendMessageFile,path,"-ios","versionIos");
        // 生成复审记录表
        creatDemoFile(tSendMessageFile,path,"-审记录表.","fsjl");

        // 生成zip
        createZip(path,name);
    }

    private void creatDemoFile(TSendMessageFile tSendMessageFile,String path, String houZhui, String tem) {
        String name = getName(tSendMessageFile);
        String fileName =  path  + name + houZhui +".xlsx";
        String templateFileName = TEMPLATE_PATH + tem + ".xlsx";
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(tSendMessageFile);
    }

    private String getName(TSendMessageFile tSendMessageFile) {
        return tSendMessageFile.getDemandNumber()+tSendMessageFile.getDemandName();
    }

    private String getPath(TSendMessageFile tSendMessageFile) {
        String name = getName(tSendMessageFile);
//        return SOURCE_PATH + name + File.separator + type + File.separator;
        return SOURCE_PATH + name + File.separator;
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
//        rabbitTemplate.convertAndSend("test.direct","test","aaaaaa哈哈哈哈或");

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
