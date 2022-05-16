package com.dhlg.module.test.tSendMessageFile.service;

import com.dhlg.module.test.tSendMessageFile.entity.TSendMessageFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since
 */
public interface ITSendMessageFileService extends IService<TSendMessageFile> {

    void saveOrUpdateCommon(TSendMessageFile tSendMessageFile, HttpServletResponse response);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result query(QueryEntity<TSendMessageFile> parameter);

    Result query(String id);

    Result getVersion();
}
