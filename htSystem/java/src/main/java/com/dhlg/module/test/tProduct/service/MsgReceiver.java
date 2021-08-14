package com.dhlg.module.test.tProduct.service;



import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;


@Slf4j
@Component
public class MsgReceiver {

//    @RabbitListener(queues = "test")
//    public void receive(Message message,String user){
//        System.out.println("message："+message);
//        System.out.println("收到消息："+user);
//    }

}
