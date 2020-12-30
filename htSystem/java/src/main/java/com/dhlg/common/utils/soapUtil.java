package com.dhlg.common.utils;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

public class soapUtil {


    static int TIMEOUT = 10000;
    /*
     * 发送soap报文
     *
     * @param message 报文体
     * @param url 服务端路径
     *
     * return：返回接收到的报文
     */
    private static String send(String message,String url) throws Exception {

        String result = null;
        org.apache.commons.httpclient.HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        // 设置连接超时
        client.getHttpConnectionManager().getParams().setConnectionTimeout(TIMEOUT);
        // 设置读取时间超时
        client.getHttpConnectionManager().getParams().setSoTimeout(TIMEOUT);
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
        result = IOToString(is);
        System.out.println(result);
        return result;
    }
    /*
     *  InputStream转String
     */
    private static String IOToString(InputStream is) throws IOException {
        if (null == is){
            return null;
        }
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(StandardCharsets.UTF_8.name());
    }

    /*
     * @param message 报文体
     * @param url 服务端路径
     *
     * return：返回map的根节点注册的map
     */
    public static Map<String, String> get(String message,String url) throws Exception {
        String result = send(message, url);
        return strXmlToDocument(result);
    }

    /*
     * 找出xml的最底层节点
     * @param parseStrXml
     *
     * return：返回map的根节点注册的map
     */
    private static Map<String, String> strXmlToDocument(String parseStrXml) throws DocumentException {
        Map<String,String> map = new HashMap<String, String>();
        Document document = DocumentHelper.parseText(parseStrXml);
        Element root = document.getRootElement();
        List<Element> list = root.elements();
        getElement(list,map);
        return map;
    }

    private static void getElement(List<Element> sonElemetList, Map<String, String> map) {
        for (Element sonElement : sonElemetList) {
            if (sonElement.elements().size() != 0) {
                getElement(sonElement.elements(),map);
            }else{
                map.put(sonElement.getName(),sonElement.getText());
            }
        }
    }


    /*
     * 获取报销code
     *
     */
    public static Map<String, String> getCode(String url) throws Exception {

        //构造发送的报文
        String content = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://WebXml.com.cn/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <web:getSupportCity>\n" +
                "         <!--Optional:-->\n" +
                "         <web:byProvinceName>江西</web:byProvinceName>\n" +
                "      </web:getSupportCity>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        return get(content, url);

    }

    /*
     * 获取状态
     *
     */
    public static Map<String, String> getState(String url) throws Exception {

        //构造发送的报文
        String content = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sch=\"http://www.aurora-framework.org/schema\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <sch:param>\n" +
                "         <sch:statementNum>JSDTEST48264934002</sch:statementNum>\n" +
                "         <sch:companyCode>2110000</sch:companyCode>\n" +
                "         <sch:billType>BX</sch:billType>\n" +
                "         <sch:email>jiangjie0116@tpl.cntaiping.com</sch:email>\n" +
                "         <sch:amount>500</sch:amount>\n" +
                "         <sch:attribute1></sch:attribute1>\n" +
                "         <sch:attribute2></sch:attribute2>\n" +
                "         <sch:attribute3></sch:attribute3>\n" +
                "         <sch:attribute4></sch:attribute4>\n" +
                "         <sch:attribute5></sch:attribute5>\n" +
                "         <sch:attribute6></sch:attribute6>\n" +
                "      </sch:param>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        return get(content, url);
    }

    @Test
    public void test() throws Exception {

        String url = "http://10.1.91.68:8001/hec/modules/ws/IT_RES/add_it_res_bill.svc";
        String content = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://WebXml.com.cn/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <web:getSupportCity>\n" +
                "         <!--Optional:-->\n" +
                "         <web:byProvinceName>江西</web:byProvinceName>\n" +
                "      </web:getSupportCity>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
//        send(url,content);
    }

    @Test
    public void test2() throws Exception {
        String url = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
        String content = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://WebXml.com.cn/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <web:getSupportCity>\n" +
                "         <!--Optional:-->\n" +
                "         <web:byProvinceName>江西</web:byProvinceName>\n" +
                "      </web:getSupportCity>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        send(content,url);
    }

}
