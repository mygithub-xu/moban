package com.dhlg.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class aaaa {

    public static void main(String[] args) {
        String wsdl = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
        StringBuffer sb = new StringBuffer("");
//        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://WebXml.com.cn/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <web:getSupportCity>\n" +
                "         <!--Optional:-->\n" +
                "         <web:byProvinceName>江西</web:byProvinceName>\n" +
                "      </web:getSupportCity>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>");
        try {
            Map<String, String> stringStringMap = soapUtil.get(sb.toString(), wsdl);
            for(String key:stringStringMap.keySet()){
                System.out.println(key+" : "+stringStringMap.get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
