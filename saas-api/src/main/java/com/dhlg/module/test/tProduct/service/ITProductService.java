package com.dhlg.module.test.tProduct.service;

import com.dhlg.module.test.tProduct.entity.TProduct;
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
public interface ITProductService extends IService<TProduct> {

    Result saveOrUpdateCommon(TProduct tProduct);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result query(QueryEntity<TProduct> parameter);

    Result query(String id);

    void send();

    Result sendMessage(String message);

    Result testxc();

    Result testxc2();
}
