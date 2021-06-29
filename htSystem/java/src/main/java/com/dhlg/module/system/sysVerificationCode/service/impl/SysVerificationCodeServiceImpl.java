package com.dhlg.module.system.sysVerificationCode.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.system.sysVerificationCode.entity.SysVerificationCode;
import com.dhlg.module.system.sysVerificationCode.dao.SysVerificationCodeMapper;
import com.dhlg.module.system.sysVerificationCode.service.ISysVerificationCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.Dictionaries;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.common.DateUtils;
import com.dhlg.utils.common.StringUtils;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dhlg.utils.GetLoginUser;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
@Service
public class SysVerificationCodeServiceImpl extends ServiceImpl<SysVerificationCodeMapper, SysVerificationCode> implements ISysVerificationCodeService {

    @Autowired
    SysVerificationCodeMapper doMapper;

    @Autowired
    private Producer producer;

    @Override
    public Result saveOrUpdateCommon(SysVerificationCode sysVerificationCode) {
        //判断新增还是修改
        if (!StringUtils.isBlank(sysVerificationCode.getId())) {
            //修改
            if(!updateById(sysVerificationCode)){

                return Result.error(Dictionaries.UPDATE_FAILED);
            }

            return Result.success(Dictionaries.SAVE_SUCCESS);
        }
        sysVerificationCode.setCreateTime(DateUtils.getCurrentDate());
        sysVerificationCode.setCreateUser(GetLoginUser.getCurrentUserId());
        sysVerificationCode.setId(StringUtils.uuid());
        if (!save(sysVerificationCode)){

            return Result.error(Dictionaries.SAVE_FAILED);
        }

        return Result.success(Dictionaries.SAVE_SUCCESS);
    }

    @Override
    public Result delete(String id) {
        if (!removeById(id)){
            return Result.error(Dictionaries.DELETE_FAILED);
        }
        return  Result.success(Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public Result query(QueryEntity<SysVerificationCode> parameter) {
        IPage<SysVerificationCode> dataList = doMapper.queryByCondition(parameter.getPage(), parameter.getCondition());
        return new Result(dataList);
    }

    @Override
    public Result query(String id) {
        return Result.success(getById(id));
    }

    @Override
    public BufferedImage getCaptcha() {
        String code = producer.createText();
        return producer.createImage(code);
    }

    @Override
    public Result deleteBatch(List<String> ids) {
        if (!removeByIds(ids)){

            return Result.error(Dictionaries.DELETE_FAILED);
        }
        return Result.success(Dictionaries.DELETE_SUCCESS);
    }
}
