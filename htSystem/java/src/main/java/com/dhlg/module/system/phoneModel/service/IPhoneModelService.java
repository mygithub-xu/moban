package com.dhlg.module.system.phoneModel.service;

import com.dhlg.module.system.phoneModel.entity.PhoneModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since
 */
public interface IPhoneModelService extends IService<PhoneModel> {

    Result saveOrUpdateCommon(PhoneModel phoneModel);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result query(QueryEntity<PhoneModel> parameter);

    Result query(String id);

    Result getModel(PhoneModel phoneModel);
}
