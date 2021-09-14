package com.dhlg.module.test.templateTree.controller;

import com.dhlg.module.test.templateTree.entity.TemplateTree;
import com.dhlg.module.test.templateTree.service.ITemplateTreeService;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.common.StringUtils;
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
@RequestMapping("/api/test/templateTree")
@CrossOrigin
public class TemplateTreeController {

        @Autowired
        ITemplateTreeService doService;

        @ApiOperation("保存或者更新")
        @PostMapping("/saveOrUpdate")
        public Result saveOrUpdate(@RequestBody TemplateTree templateTree) {
                if (StringUtils.isBlank(templateTree)) {
                        throw new ParamIsNullException();
                }
                return doService.saveOrUpdateCommon(templateTree);
        }

        @ApiOperation("获取tree状结构1")
        @PostMapping("/query")
        public Result query() {
                return doService.getTree();
        }

        @ApiOperation("获取tree状结构2")
        @PostMapping("/query2")
        public Result query2() {
                return doService.getTree2();
        }

}


