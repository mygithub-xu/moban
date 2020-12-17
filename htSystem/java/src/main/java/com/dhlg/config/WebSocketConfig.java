package com.dhlg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 描述
 * User:whfch
 * Date:2020/3/13
 * Time:21:38
 */
@Configuration
public class WebSocketConfig{

    //打包时注释掉这个方法，以免tomcat发生错误
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }
}
