package com.dhlg.module.test.lianxi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class test {

    public static void main(String[] args) {

//        String json = "{\"version\":\\\"v3\",\\\"coverPrdId\\\":4149,\\\"currentInsuredIndex\\\":0,\\\"holder\\\":{\\\"name\\\":\\\"\\\",\\\"gender\\\":\\\"M\\\",\\\"age\\\":30,\\\"birthday\\\":\\";
//
//        json = json.replaceAll("\\\\","");
//
//        System.out.println(json);//输出 Hello
//
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        String format = sdf.format(new Date());
//        System.out.println(format.replaceAll("/",""));
//        int[] aa = {1,2,3};
//         String aaaaa = String.valueOf(aa[0]);
//
//        int count = 0;
//        for (int i = 0; i < 10; i++) {
//            count = count++;
//
//        }

        String s = "xxxxxxxxxxxxxxx#123#456#zzzzz";
        int n = s.indexOf("#");
        int k = s.indexOf("#", n+1);
        System.out.println(n+"  :  "+k);
        String s2 = s.substring(n+2, k);
        System.out.println(s2);
    }

}
