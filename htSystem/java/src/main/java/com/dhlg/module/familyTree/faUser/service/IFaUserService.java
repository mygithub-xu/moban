package com.dhlg.module.familyTree.faUser.service;

import com.dhlg.module.familyTree.faUser.entity.FaUser;
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
public interface IFaUserService extends IService<FaUser> {

    Result saveOrUpdateCommon(FaUser faUser);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result query(QueryEntity<FaUser> parameter);

    Result query(String id);

    Result listTree();
}
