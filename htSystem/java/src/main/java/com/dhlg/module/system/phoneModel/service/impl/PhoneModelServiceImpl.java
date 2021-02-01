package com.dhlg.module.system.phoneModel.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.exception.ParamIsNullException;
import com.dhlg.module.system.phoneModel.entity.PhoneModel;
import com.dhlg.module.system.phoneModel.dao.PhoneModelMapper;
import com.dhlg.module.system.phoneModel.service.IPhoneModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.module.system.sysAutoParam.entity.SysAutoParam;
import com.dhlg.utils.Dictionaries;
import com.dhlg.utils.Parameter.Parameter;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.StringUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
@Service
public class PhoneModelServiceImpl extends ServiceImpl<PhoneModelMapper, PhoneModel> implements IPhoneModelService {

    @Autowired
    PhoneModelMapper doMapper;

    @Override
    public Result saveOrUpdateCommon(PhoneModel phoneModel) {
        //判断新增还是修改
        if (!StringUtils.isBlank(phoneModel.getId())) {
            //修改
            if(!updateById(phoneModel)){

                return Result.error(Dictionaries.UPDATE_FAILED);
            }

            return Result.success(Dictionaries.SAVE_SUCCESS);
        }
        phoneModel.setId(StringUtils.uuid());
        if (!save(phoneModel)){

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
    public Result query(QueryEntity<PhoneModel> parameter) {
        IPage<PhoneModel> dataList = doMapper.queryByCondition(parameter.getPage(), parameter.getCondition());
        return new Result(dataList);
    }

    @Override
    public Result query(String id) {
        return Result.success(getById(id));
    }

    @Override
    public Result getModel(PhoneModel phoneModel) {
        if (StringUtils.isBlank(phoneModel.getTerminalModel())){
            throw new ParamIsNullException();
        }
        PhoneModel dataPhone = isexit(phoneModel.getTerminalModel());
        if (!StringUtils.isBlank(dataPhone)){
            return Result.success(dataPhone);
        }
        String url = "http://www.wholedata.top:81/getAllBehavior";
        Map<String, String> params = new HashMap<>();
        params.put("needParam",phoneModel.getTerminalModel());
        JSONObject json = postParam(params, url);
        phoneModel.setId(StringUtils.uuid());
        if (!StringUtils.isBlank(json)){

            phoneModel.setTerminalName(json.getString("终端名称"));
            phoneModel.setTerminalNameType(json.getString("终端类型名称"));
            phoneModel.setTerminalNumber(json.getString("终端编号"));
            phoneModel.setTerminalManufacturer(json.getString("终端厂商名称"));
            phoneModel.setResultType("1");

        }else {
            phoneModel.setResultType("0");
        }
        if (!save(phoneModel)){
            return Result.error(Dictionaries.SAVE_FAILED);
        }
        return Result.success(phoneModel);
    }

    private PhoneModel isexit(String terminalModel) {
        PhoneModel phoneModel = getOne(new QueryWrapper<PhoneModel>().eq("terminal_model", terminalModel));
        return phoneModel;
    }

    private JSONObject postParam(Map<String, String> params, String url) {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        JSONObject result;
        //将原来写入postman中的form-data数据放入Map中
        for (Map.Entry<String, String> entry : params.entrySet()) {
            method.addParameter(entry.getKey(),entry.getValue());
        }
        try {
            client.executeMethod(method);
            byte[] response1 = method.getResponseBody();
            //获取请求后的响应体
            String result1 = new String(response1,"UTF-8");
            JSONObject jsonObject = JSONObject.parseObject(result1);
            result = JSONObject.parseObject(jsonObject.getString("AGENT"));
            System.out.println(result.getString("终端名称"));

        } catch (IOException ex) {
            result = null;
        } finally {
            //释放连接
            method.releaseConnection();
        }
        return result;
    }
    @Override
    public Result deleteBatch(List<String> ids) {
        if (!removeByIds(ids)){

            return Result.error(Dictionaries.DELETE_FAILED);
        }
        return Result.success(Dictionaries.DELETE_SUCCESS);
    }
}
