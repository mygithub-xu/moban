package com.dhlg.module.system.sysAutoTable.controller;

import com.dhlg.module.system.sysAutoTable.entity.SysAutoTable;
import com.dhlg.module.system.sysAutoTable.service.ISysAutoTableService;
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
@RequestMapping("/api/system/sysAutoTable")
@CrossOrigin
public class SysAutoTableController {

        @Autowired
        ISysAutoTableService doService;


        @ApiOperation("保存或者更新")
        @PostMapping("/saveOrUpdate")
        public Result saveOrUpdate(@RequestBody SysAutoTable sysAutoTable) {
                if(StringUtils.isBlank(sysAutoTable)){
                        throw new ParamIsNullException();
                }
                return  doService.customSaveOrUpdate(sysAutoTable);
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

        @ApiOperation("获取树状数据")
        @PostMapping("/getNodeList")
        public Result getNodeList() {
                return  doService.getNodeList();
        }
}

