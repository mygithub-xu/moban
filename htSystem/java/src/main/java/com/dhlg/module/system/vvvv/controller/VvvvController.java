package com.dhlg.module.system.vvvv.controller;

import com.alibaba.fastjson.JSONObject;
import com.dhlg.module.system.vvvv.entity.Vvvv;
import com.dhlg.module.system.vvvv.service.IVvvvService;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.StringUtils;
import com.dhlg.exception.ParamIsNullException;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
@RequestMapping("/api/system/vvvv")
@CrossOrigin
public class VvvvController {

        @Autowired
        IVvvvService doService;

        @ApiOperation("保存或者更新")
        @PostMapping("/saveOrUpdate")
        public Result saveOrUpdate(@RequestBody Vvvv vvvv) {
                if(StringUtils.isBlank(vvvv)){
                        throw new ParamIsNullException();
                }
                return  doService.saveOrUpdateCommon(vvvv);
        }

        @ApiOperation("删除（物理删除）")
        @DeleteMapping("/deleteById/{id}")
        public Result delete(@PathVariable String id) {

                return doService.delete(id);
        }

        @ApiOperation("查询")
        @PostMapping("/query")
        public Result query(@RequestBody QueryEntity<Vvvv> parameter) {
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

        public Result savePhone(@RequestBody String parameter){
                String url = "http://www.wholedata.top:81/getAllBehavior";
                Map<String, String> params = new HashMap<>();
                params.put("needParam","IPhone");
                JSONObject param = postParam(params, url);
                if (StringUtils.isBlank(param)){

                }
                return Result.success(param);
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
}

