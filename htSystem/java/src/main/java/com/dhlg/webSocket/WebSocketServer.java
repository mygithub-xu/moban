package com.dhlg.webSocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


import com.dhlg.config.WebSocketConfig;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 描述
 * User:whfch
 * Date:2020/3/13
 * Time:23:30
 */

@ServerEndpoint(value="/websocket/{fromId}")
@Component
public class WebSocketServer {

    private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    private static Map<String,WebSocketServer> connections = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //用户id
    private String fromId="";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("fromId") String fromId) {
        this.session = session;
        if (!connections.containsKey(fromId)){
            getOnlineCount();           //在线数加1
        }
        connections.put(fromId,this);     //加入set中

        log.info("有新窗口开始监听:"+fromId+",当前在线人数为" + getOnlineCount());
        try {
            //给所有人发通知，，暂时不用
            sendMessageAll("我已上线",fromId);
            //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
        } catch (Exception e) {
            log.error("上线时通知全体时异常");
        }
    }

    private void sendMessageAll(String message,String fromId) {
        if (connections.size()==0){
            return;
        }
//        for (WebSocketServer item : connections.values()) {
//            item.session.getAsyncRemote().sendText(fromId+":"+message);
//        }
    }

    //发送消息
//    public void sendMessage(MbSysChat chat) {
//        WebSocketServer to=connections.get(chat.getToId());
//        if (to!=null){
//            try {
//                JSONObject json = JSONObject.fromObject(chat);
//                to.session.getBasicRemote().sendText(String.valueOf(json));
//            } catch (IOException e) {
//                log.error("发送消息时出错");
//            }
//        }
//    }
    //发送通知
    public void sendNotice(String message,String[] ToIds) {
        for (String id:ToIds){
            WebSocketServer to=connections.get(id);
            if (to!=null){
                try {
                    to.session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    log.error("发送公告时出错");
                }
            }
        }


    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        connections.remove(fromId);  //从set中删除
        getOnlineCount();           //在线数减1
        try {
            //给所有人发通知，，暂时不用
            sendMessageAll("我已下线",fromId);
            //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
        } catch (Exception e) {
            log.error("下线时通知全体异常");
        }

        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {

        log.info("收到来自窗口"+fromId+"的信息:"+message);

        //群发消息
//        for (WebSocketServer item : webSocketSet) {
//            try {
////                item.sendInfo(message,data);
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }


//    /**
//     * 发送消息
//     * */
//    public  void send(String message,String fromId,String toID) throws IOException {
//        log.info("推送消息到窗口"+fromId+"，推送内容:"+message);
//
//
//    }

    public static synchronized int getOnlineCount() {
        return connections.size();
    }
}
