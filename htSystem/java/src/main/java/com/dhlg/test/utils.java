package com.dhlg.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class utils {

    public static void main(String[] args) {
        String pat = "(Linux; Android 6.0; NEM-AL10 Build/HONORNEM-AL10; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/63.0.3239.111 Mobile Safari/537.36 lite baiduboxapp/2.7.0.10 (Baidu; P1 6.0)";
        Pattern pattern = Pattern.compile("(iPhone; CPU|Android.*;)\\s(.*?)\\s(Build/|OS)");
        Matcher matcher = pattern.matcher(pat);
        if (matcher.find() && matcher.groupCount() == 3) {
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
        }
    }
}
