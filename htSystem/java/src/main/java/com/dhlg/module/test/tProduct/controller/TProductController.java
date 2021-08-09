package com.dhlg.module.test.tProduct.controller;

import com.dhlg.module.test.tProduct.entity.TProduct;
import com.dhlg.module.test.tProduct.service.ITProductService;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;

import com.dhlg.exception.ParamIsNullException;
import com.dhlg.utils.common.StringUtils;
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
@RequestMapping("/api/test/tProduct")
@CrossOrigin
public class TProductController {

        @Autowired
        ITProductService doService;

        @ApiOperation("保存或者更新")
        @PostMapping("/saveOrUpdate")
        public Result saveOrUpdate(@RequestBody TProduct tProduct) {
                if(StringUtils.isBlank(tProduct)){
                        throw new ParamIsNullException();
                }
                Object aa = tProduct;
                aa.equals("aaa");
                return  doService.saveOrUpdateCommon(tProduct);
        }

        @ApiOperation("删除（物理删除）")
        @DeleteMapping("/deleteById/{id}")
        public Result delete(@PathVariable String id) {

                return doService.delete(id);
        }

        @ApiOperation("查询")
        @PostMapping("/query")
        public Result query(@RequestBody QueryEntity<TProduct> parameter) {
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

