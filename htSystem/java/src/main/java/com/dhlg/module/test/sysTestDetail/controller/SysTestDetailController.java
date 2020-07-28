package com.dhlg.module.test.sysTestDetail.controller;

import com.dhlg.module.test.sysTestDetail.entity.SysTestDetail;
import com.dhlg.module.test.sysTestDetail.service.ISysTestDetailService;
import com.dhlg.utils.common.Parameter.Parameter;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.utils.common.exception.ParamIsNullException;
import com.dhlg.utils.common.uploadDown.ExcelUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/api/test/sysTestDetail")
@CrossOrigin
public class SysTestDetailController {

        @Autowired
        ISysTestDetailService doService;


        @ApiOperation("保存或者更新")
        @PostMapping("/saveOrUpdate")
        public Result saveOrUpdate(@RequestBody SysTestDetail sysTestDetail) {
                if(StringUtils.isBlank(sysTestDetail)){
                        throw new ParamIsNullException();
                }
                return  doService.customSaveOrUpdate(sysTestDetail);
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

        @ApiOperation("根据条件分页获取按钮数据")
        @PostMapping("/querybycondition")
        public Result queryByCondition(@RequestBody Parameter parameter) {
                if(StringUtils.isBlank(parameter)){
                        throw new ParamIsNullException();
                }
                return  doService.queryByCondition(parameter);
        }

        @ApiOperation("列表字段查询")
        @PostMapping("/listFieldQuery")
        public Result listFieldQuery(@RequestBody Map<String, Object> params) {
        return  doService.listFieldQuery(params);
        }


}

