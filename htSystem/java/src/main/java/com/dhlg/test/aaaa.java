package com.dhlg.test;

import java.util.ArrayList;
import java.util.List;

public class aaaa {

    public static void main(String[] args) {
        String wsdl = "http://localhost:9000/HelloWorld?wsdl";
        int timeout = 10000;
        StringBuffer sb = new StringBuffer("");
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:test=\"http://test.dhlg.com/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <test:sayHello>\n" +
                "         <!--Optional:-->\n" +
                "         <arg0>oooooo</arg0>\n" +
                "      </test:sayHello>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>");
        soapUtil.get(sb.toString(),wsdl);

    }
}
