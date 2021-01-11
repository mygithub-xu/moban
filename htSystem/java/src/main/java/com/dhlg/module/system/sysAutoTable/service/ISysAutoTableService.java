package com.dhlg.module.system.sysAutoTable.service;

import com.dhlg.module.system.sysAutoTable.entity.SysAutoTable;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.common.utils.Parameter.Parameter;
import com.dhlg.common.utils.Result;

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

    Result queryByCondition(Parameter parameter);

    Result listFieldQuery(Map<String, Object> params);

    Result getNodeList();

    Result existable(SysAutoTable sysAutoTable);

    Result findByID(String id);
}
