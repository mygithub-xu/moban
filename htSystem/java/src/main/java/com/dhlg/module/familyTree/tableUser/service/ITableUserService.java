package com.dhlg.module.familyTree.tableUser.service;

import com.dhlg.module.familyTree.tableUser.entity.TableUser;
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
public interface ITableUserService extends IService<TableUser> {

    Result saveOrUpdateCommon(TableUser tableUser);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result query(QueryEntity<TableUser> parameter);

    Result query(String id);

}
