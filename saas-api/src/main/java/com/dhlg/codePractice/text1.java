package com.dhlg.codePractice;

import java.util.ArrayList;

public class text1 {
    public static void main(String[] args) {
        // 替换空格
        String str = "We Are Happy";
        replaceSpace(str);
    }
    public static String replaceSpace(String s) {
        return  s.replace(" ","%20");
    }
}
