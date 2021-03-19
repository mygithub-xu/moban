package com.dhlg.module.familyTree.faTable.service;

import com.dhlg.module.familyTree.faTable.entity.FaTable;
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
public interface IFaTableService extends IService<FaTable> {

    Result saveOrUpdateCommon(FaTable faTable);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result query(QueryEntity<FaTable> parameter);

    Result query(String id);

}
