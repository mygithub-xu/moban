package com.dhlg.module.system.mbSysChat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlg.module.system.mbSysChat.entity.MbSysChat;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xu
 * @since 2020-03-14
 */
public interface MbSysChatMapper extends BaseMapper<MbSysChat> {

    List<MbSysChat> findChatList(String id);

    List<MbSysChat> findChatListById(String id);

    void changeChatStatus(@Param("ids") String[] ids);
}
