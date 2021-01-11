package com.dhlg.module.system.sysAutoParam.controller;

import com.dhlg.module.system.sysAutoParam.entity.SysAutoParam;
import com.dhlg.module.system.sysAutoParam.service.ISysAutoParamService;
import com.dhlg.common.utils.Parameter.Parameter;
import com.dhlg.common.utils.Result;
import com.dhlg.common.utils.StringUtils;
import com.dhlg.common.utils.exception.ParamIsNullException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/system/sysAutoParam")
@CrossOrigin
public class SysAutoParamController {

        @Autowired
        ISysAutoParamService doService;


        @ApiOperation("保存或者更新")
        @PostMapping("/saveOrUpdate")
        public Result saveOrUpdate(@RequestBody SysAutoParam sysAutoParam) {
                if(StringUtils.isBlank(sysAutoParam)){
                        throw new ParamIsNullException();
                }
                return  doService.customSaveOrUpdate(sysAutoParam);
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

        @ApiOperation("根据父id找到表字段")
        @GetMapping("/findByTableID/{id}")
        public Result findByTableID(@PathVariable String id) {
                return  doService.findByTableID(id);
        }
}

