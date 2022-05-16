package com.dhlg.module.system.mbSysChat.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlg.module.system.mbSysChat.entity.MbSysChat;
import com.dhlg.utils.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since 2020-03-14
 */
public interface IMbSysChatService extends IService<MbSysChat> {

    Result send(MbSysChat chat);

    Result findChatList(String id);

    void changeChatStatus(List<String> ids);

    Result sendAll(MbSysChat chat);
}
