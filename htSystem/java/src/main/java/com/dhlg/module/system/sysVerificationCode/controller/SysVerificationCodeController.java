package com.dhlg.module.system.sysVerificationCode.controller;

import com.dhlg.module.system.sysVerificationCode.entity.SysVerificationCode;
import com.dhlg.module.system.sysVerificationCode.service.ISysVerificationCodeService;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.exception.ParamIsNullException;
import com.dhlg.utils.common.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
@RequestMapping("/api/system/sysVerificationCode")
@CrossOrigin
public class SysVerificationCodeController {

        @Autowired
        ISysVerificationCodeService doService;

        @ApiOperation("保存或者更新")
        @PostMapping("/saveOrUpdate")
        public Result saveOrUpdate(@RequestBody SysVerificationCode sysVerificationCode) {
                if(StringUtils.isBlank(sysVerificationCode)){
                        throw new ParamIsNullException();
                }
                return  doService.saveOrUpdateCommon(sysVerificationCode);
        }

        @ApiOperation("删除（物理删除）")
        @DeleteMapping("/deleteById/{id}")
        public Result delete(@PathVariable String id) {

                return doService.delete(id);
        }

        @ApiOperation("查询")
        @PostMapping("/query")
        public Result query(@RequestBody QueryEntity<SysVerificationCode> parameter) {
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

        @ApiOperation("批量删除（物理删除）")
        @GetMapping("/getCode")
        public void getCode(HttpServletResponse response) throws IOException {
                response.setHeader("Cache-Control", "no-store, no-cache");
                response.setContentType("image/jpeg");

                //获取图片验证码
                BufferedImage image = doService.getCaptcha();

                ServletOutputStream out = response.getOutputStream();
                ImageIO.write(image, "jpg", out);
                IOUtils.closeQuietly(out);
        }
}

