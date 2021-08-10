package com.dhlg.module.test.tSendMessageFile.controller;

import com.dhlg.module.test.tSendMessageFile.entity.TSendMessageFile;
import com.dhlg.module.test.tSendMessageFile.service.ITSendMessageFileService;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.exception.ParamIsNullException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xu
 * @since
 */
@RestController
@RequestMapping("/api/test/tSendMessageFile")
@Api(value = "邮件附件发送接口")
@CrossOrigin
public class TSendMessageFileController {

        @Autowired
        ITSendMessageFileService doService;

        @ApiOperation("保存或者更新")
        @PostMapping("/downZip")
        public void saveOrUpdate(@RequestBody TSendMessageFile tSendMessageFile, HttpServletResponse response) {
                if(StringUtils.isBlank(tSendMessageFile)){
                        throw new ParamIsNullException();
                }
                doService.saveOrUpdateCommon(tSendMessageFile, response);
        }

        @ApiOperation("删除（物理删除）")
        @DeleteMapping("/deleteById/{id}")
        public Result delete(@PathVariable String id) {
                return doService.delete(id);
        }

        @ApiOperation("查询")
        @PostMapping("/query")
        public Result query(@RequestBody QueryEntity<TSendMessageFile> parameter) {
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

        @ApiOperation("获取版本号")
        @GetMapping("/getVersion")
        public Result getVersion() {
                return  doService.getVersion();
        }

}

