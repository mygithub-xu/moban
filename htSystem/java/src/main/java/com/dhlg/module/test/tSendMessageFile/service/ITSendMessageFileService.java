package com.dhlg.module.test.tSendMessageFile.service;

import com.dhlg.module.test.tSendMessageFile.entity.TSendMessageFile;
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
public interface ITSendMessageFileService extends IService<TSendMessageFile> {

    Result saveOrUpdateCommon(TSendMessageFile tSendMessageFile);

    Result delete(String id);

    Result deleteBatch(List<String> ids);

    Result query(QueryEntity<TSendMessageFile> parameter);

    Result query(String id);

    Result getVersion();
}
