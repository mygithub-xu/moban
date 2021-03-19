package com.dhlg.module.familyTree.faTable.controller;

import com.dhlg.module.familyTree.faTable.entity.FaTable;
import com.dhlg.module.familyTree.faTable.service.IFaTableService;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.StringUtils;
import com.dhlg.exception.ParamIsNullException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/familyTree/faTable")
@CrossOrigin
public class FaTableController {

        @Autowired
        IFaTableService doService;

        @ApiOperation("保存或者更新")
        @PostMapping("/saveOrUpdate")
        public Result saveOrUpdate(@RequestBody FaTable faTable) {
                if(StringUtils.isBlank(faTable)){
                        throw new ParamIsNullException();
                }
                return  doService.saveOrUpdateCommon(faTable);
        }

        @ApiOperation("删除（物理删除）")
        @DeleteMapping("/deleteById/{id}")
        public Result delete(@PathVariable String id) {

                return doService.delete(id);
        }

        @ApiOperation("查询")
        @PostMapping("/query")
        public Result query(@RequestBody QueryEntity<FaTable> parameter) {
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
}

