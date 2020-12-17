package com.dhlg.test;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class soapUtil {

    /*
     * 发送soap报文
     *
     * @param message 报文体
     * @param url 服务端路径
     *
     * return：返回接收到的报文
     */
    private static String send(String message,String url){
        int timeout = 10000;
        String result = null;
        org.apache.commons.httpclient.HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        // 设置连接超时
        client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
        // 设置读取时间超时
        client.getHttpConnectionManager().getParams().setSoTimeout(timeout);
        try {
            // 然后把Soap请求数据添加到PostMethod中
            RequestEntity requestEntity = new StringRequestEntity(message, "text/xml", "UTF-8");
            //设置请求头部，否则可能会报 “no SOAPAction header” 的错误
//        postMethod.setRequestHeader("SOAPAction","");
            // 设置请求体
            postMethod.setRequestEntity(requestEntity);
            client.executeMethod(postMethod);
            // 获取响应体输入流
            InputStream is = postMethod.getResponseBodyAsStream();
            // 获取请求结果字符串
            result = IOUtils.toString(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Map<String, String> get(String message,String url){
        String result = send(message, url);
        Map<String,String> map = new HashMap<String, String>();
        if (null==result){
            map.put("code","500");
        }else {
            strXmlToDocument(result,map);
            map.put("code","200");
        }
        for(String key : map.keySet()){
            System.out.println(key+":"+map.get(key));
        }
        return map;
    }

    private static void strXmlToDocument(String parseStrXml, Map<String, String> map){
        try {
            Document document = DocumentHelper.parseText(parseStrXml);
            Element root = document.getRootElement();
            List<Element> list = root.elements();
            getElement(list,map);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private static Map<String,String> getElement(List<Element> sonElemetList, Map<String, String> map) {
        for (Element sonElement : sonElemetList) {
            if (sonElement.elements().size() != 0) {
                getElement(sonElement.elements(),map);
            }else{
                map.put(sonElement.getName(),sonElement.getText());
            }

        }

        return map;
    }

}
