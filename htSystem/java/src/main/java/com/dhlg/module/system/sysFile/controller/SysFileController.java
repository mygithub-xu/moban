package com.dhlg.module.system.sysFile.controller;


import com.dhlg.module.system.sysFile.service.ISysFileService;
import com.dhlg.utils.Parameter.Parameter;
import com.dhlg.utils.Result;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.exception.ParamIsNullException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xu
 * @since 2020-05-01
 */
@RestController
@RequestMapping("/api/system/sysFile")
@Slf4j
public class SysFileController {
    @Autowired
    ISysFileService doservice;

    @ApiOperation("文件上传")
    @PostMapping(value = "/uploadFile")
    public Result uploadFile(@RequestParam("file") MultipartFile file){

        if(StringUtils.isBlank(file)){
            throw new ParamIsNullException();
        }
        return doservice.uploadFile(file);
    }

    @ApiOperation("多图上传")
    @PostMapping(value = "/uploadFileMore")
    public Result uploadFileMore(@RequestParam("file") MultipartFile file,@RequestParam String type){

        if(StringUtils.isBlank(file)){
            throw new ParamIsNullException();
        }
        return doservice.uploadFileMore(file,type);
    }

    @ApiOperation("删除图片")
    @PostMapping(value = "/deleteFile/{id}")
    public Result deleteFile(@PathVariable String id){

        if(StringUtils.isBlank(id)){
            throw new ParamIsNullException();
        }
        return doservice.deleteFile(id);
    }

    @ApiOperation("批量删除")
    @PostMapping("/batchdelete")
    public Result batchDelete(@RequestBody List<String> ids) {

        return doservice.deleteBatch(ids);
    }

    @ApiOperation("根据条件分页获取按钮数据")
    @PostMapping("/querybycondition")
    public Result queryByCondition(@RequestBody Parameter parameter) {
        if(StringUtils.isBlank(parameter)){
            throw new ParamIsNullException();
        }
        return  doservice.queryByCondition(parameter);
    }

    @ApiOperation("根据条件分页获取按钮数据")
    @PostMapping("/down/{id}")
    public Result down(@PathVariable String id) {
        if(StringUtils.isBlank(id)){
            throw new ParamIsNullException();
        }
        return  doservice.down(id);
    }

    @Test
    public void text(){
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379,-1,"hehe");

        //向JedisPool借用8次连接，但是没有执行归还操作。
        for (int i = 0; i < 6; i++) {
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();
                jedis.ping();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        jedisPool.getResource().ping();
    }
}

