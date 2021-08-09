package com.dhlg.module.test.sysTest.controller;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class tttt {


    public static void main(String[] args) {
        // 测试对象空
        Map o = new HashMap();
        System.out.println(strCheck(o));
        System.out.println(isBlank(o));
        System.out.println(o.isEmpty());
    }


    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ways = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for (int l : left) {
                    for (int r : right) {
                        switch (c) {
                            case '+':
                                ways.add(l + r);
                                break;
                            case '-':
                                ways.add(l - r);
                                break;
                            case '*':
                                ways.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        if (ways.size() == 0) {
            ways.add(Integer.valueOf(input));
        }
        return ways;
    }

    public static boolean strCheck(Object str) {
        if (null == str) {
            return false;
        }
        String temp = str.toString().trim();
        if ("".equals(temp) || "null".equals(temp) || "undefined".equals(temp)) {
            return false;
        }
        return true;
    }
    public static boolean isBlank(Object... objects) {
        Boolean result = false;
        for (Object object : objects) {
            if (null == object || "".equals(object.toString().trim())
                    || "null".equals(object.toString().trim())) {
                result = true;
                break;
            }
        }
        return !result;
    }
}
