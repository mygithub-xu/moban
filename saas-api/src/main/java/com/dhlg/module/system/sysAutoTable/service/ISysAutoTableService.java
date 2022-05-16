package com.dhlg.module.system.sysAutoTable.service;

import com.dhlg.module.system.sysAutoTable.entity.ProjModel;
import com.dhlg.module.system.sysAutoTable.entity.SysAutoTable;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.Parameter.Parameter;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since
 */
public interface ISysAutoTableService extends IService<SysAutoTable> {

    Result customSaveOrUpdate(SysAutoTable sysAutoTable) throws SQLException;

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result queryByCondition(QueryEntity<SysAutoTable> parameter);

    Result listFieldQuery(Map<String, Object> params);

    Result getNodeList();

    Result existable(SysAutoTable sysAutoTable);

    Result findByID(String id);

    Result codeGeneration(ProjModel projModel);

    Result saveOrUpdateTem(SysAutoTable autoTable);

    Result findTable();

    Result findTableField(String tableName);
}
