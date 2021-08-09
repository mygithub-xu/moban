package com.dhlg.module.test.tSendMessageFile.service.impl;

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
import com.dhlg.utils.common.HttpUtils;
import com.dhlg.utils.common.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dhlg.utils.GetLoginUser;
import com.dhlg.utils.common.DateUtils;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
@Service
public class TSendMessageFileServiceImpl extends ServiceImpl<TSendMessageFileMapper, TSendMessageFile> implements ITSendMessageFileService {

    @Autowired
    TSendMessageFileMapper doMapper;

    @Override
    public Result saveOrUpdateCommon(TSendMessageFile tSendMessageFile) {
        //判断新增还是修改
        if (!StringUtils.isBlank(tSendMessageFile.getId())) {
            //修改
            tSendMessageFile.setUpdateTime(DateUtils.getCurrentDate());
            tSendMessageFile.setCreateUser(GetLoginUser.getCurrentUserId());
            if(!updateById(tSendMessageFile)){

                return Result.error(Dictionaries.UPDATE_FAILED);
            }

            return Result.success(Dictionaries.SAVE_SUCCESS);
        }
        tSendMessageFile.setCreateUser(GetLoginUser.getCurrentUserId());
        tSendMessageFile.setCreateTime(DateUtils.getCurrentDate());
        tSendMessageFile.setId(StringUtils.uuid());
        if (!save(tSendMessageFile)){

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
