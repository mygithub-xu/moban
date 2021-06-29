package com.dhlg.module.system.sysVerificationCode.service;

import com.dhlg.module.system.sysVerificationCode.entity.SysVerificationCode;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since
 */
public interface ISysVerificationCodeService extends IService<SysVerificationCode> {

    Result saveOrUpdateCommon(SysVerificationCode sysVerificationCode);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result query(QueryEntity<SysVerificationCode> parameter);

    Result query(String id);

    BufferedImage getCaptcha();
}
