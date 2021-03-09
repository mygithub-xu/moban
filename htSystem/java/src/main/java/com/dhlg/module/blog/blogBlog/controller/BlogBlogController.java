package com.dhlg.module.blog.blogBlog.controller;

import com.dhlg.exception.ParamIsNullException;
import com.dhlg.module.blog.blogBlog.entity.BlogBlog;
import com.dhlg.module.blog.blogBlog.service.IBlogBlogService;
import com.dhlg.utils.Parameter.Parameter;
import com.dhlg.utils.Result;
import com.dhlg.utils.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/blog/blogBlog")
@CrossOrigin
public class BlogBlogController {

        @Autowired
        IBlogBlogService doService;


        @ApiOperation("保存或者更新")
        @PostMapping("/saveOrUpdate")
        public Result saveOrUpdate(@RequestBody BlogBlog blogBlog) {
                if(StringUtils.isBlank(blogBlog)){
                        throw new ParamIsNullException();
                }
                return  doService.customSaveOrUpdate(blogBlog);
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


        @ApiOperation("测试")
        @GetMapping("/text")
        public String text() {

                return "access_token=623239c3760c600d81ed551e6d2adf4fwefwqd5a1ed24d8f959ac44b2b82b&expires=-1950167928";
        }

        @ApiOperation("测试2")
        @GetMapping("/text2")
        public Map<String,Object> text2() {
                Map<String,Object> aa=new HashMap<>();
                aa.put("accountId","200010002");
                aa.put("mobile","");
                aa.put("userName","张7121");
                aa.put("uid","200010002");
                aa.put("email","xuhh0324@tpl.cntaiping.com");
                return aa;
        }

}

