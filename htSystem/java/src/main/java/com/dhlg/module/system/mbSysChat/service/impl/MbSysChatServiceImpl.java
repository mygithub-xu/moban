package com.dhlg.module.system.mbSysChat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.module.system.mbSysChat.dao.MbSysChatMapper;
import com.dhlg.module.system.mbSysChat.entity.MbSysChat;
import com.dhlg.module.system.mbSysChat.service.IMbSysChatService;
import com.dhlg.utils.Dictionaries;
import com.dhlg.utils.Result;
import com.dhlg.utils.common.DateUtils;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.webSocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-03-14
 */
@Service
public class MbSysChatServiceImpl extends ServiceImpl<MbSysChatMapper, MbSysChat> implements IMbSysChatService {

    @Autowired
    WebSocketServer webSocketServer;

    @Autowired
    MbSysChatMapper doMapper;

    @Override
    public Result send(MbSysChat chat) {

        chat.setId(StringUtils.uuid());
        chat.setTime(DateUtils.getCurrentDate());
        chat.setTimeStamp(chat.getTime().getTime());

        try {
            webSocketServer.sendMessage(chat);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送失败");
        }

        if (!super.save(chat)){
            return new Result("500", Dictionaries.SEND_MESSAGE_ERROR,Dictionaries.SEND_MESSAGE_ERROR);
        }
        return new Result("200", chat,Dictionaries.SEND_MESSAGE_SUCCESS);
    }

    @Override
    public Result findChatList(String id) {

        //找出聊天列表
        List<MbSysChat> chatList=doMapper.findChatList(id);
        //找出未读消息，
        List<MbSysChat> chats=doMapper.findChatListById(id);
        List<MbSysChat> mbchats;
        List<MbSysChat> mbchatList;
        mbchats=changeChatList(chats);
        mbchatList=changeChatList(chatList);

        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("chats",mbchats);
        dataMap.put("chatList",mbchatList);

        return new Result("200",dataMap,"");
    }

    @Override
    public void changeChatStatus(List<String> ids) {
        String[] idList= ids.toArray(new String[0]);
        doMapper.changeChatStatus(idList);
    }

    @Override
    public Result sendAll(MbSysChat chat) {
        chat.setId(StringUtils.uuid());
        chat.setTime(DateUtils.getCurrentDate());
        chat.setTimeStamp(chat.getTime().getTime());

        try {
            webSocketServer.sendMessageAllChat(chat);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送失败");
        }

        if (!super.save(chat)){
            return new Result("500", Dictionaries.SEND_MESSAGE_ERROR,Dictionaries.SEND_MESSAGE_ERROR);
        }
        return new Result("200", chat,Dictionaries.SEND_MESSAGE_SUCCESS);
    }

    private List<MbSysChat> changeChatList(List<MbSysChat> chats) {
        if(!StringUtils.isBlank(chats)){
            for (MbSysChat chat:chats){
                //转换时间
                Long aa=chat.getTime().getTime();
                chat.setTimeStamp(aa);
                if (!StringUtils.isBlank(chat.getHeadPortrait())){
                    try {
                        byte[]  bytes = new BASE64Decoder().decodeBuffer(new String(chat.getHeadPortrait()));
                        String baseString = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
                        chat.setHeadPortraitSrc("data:image/png;base64,"+baseString);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return chats;
    }
}
