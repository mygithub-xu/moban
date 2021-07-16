package com.dhlg.module.test.sysTest.controller;

public class textjhhhh {
    public static void main(String[] args) {
        String s = "{}{}{}{}{}{(([[]]))]";
        boolean valid = isValid(s);
        System.out.println(valid);
    }
    static boolean isValid(String s) {
        if ("()".equals(s) || "[]".equals(s) || "{}".equals(s)){
            return true;
        }
        char[] cha = s.toCharArray();
        if (cha.length % 2 != 0){
            return false;
        }
        // 将其分为若干块，记录第一块
        char firstChar;
        int count = 0;
        int countBack = 0;
        int init = 0;
        boolean flag = false;
        for (int i = 0; i < cha.length; i++) {
            firstChar = cha[init];
            if (firstChar==')'||firstChar=='}'||firstChar==']'){
                flag = false;
                break;
            }
            char firstCharBack = getBack(firstChar);
            // 遇见相同累加
            if (cha[i] == firstChar){
                count++;
            }
            // 遇见相反闭合
            if (cha[i] == firstCharBack){
                countBack ++;
                if (count == countBack){
                    // 截取
                    String substring = s.substring(init+1, i);
                    if (substring.isEmpty()){
                        flag = true;
                    }else {
                        flag = isValid(substring);
                    }
                    if (!flag) {
                        break;
                    }
                    init = i+1;
                }
            }

            if (i == cha.length-1&&cha[i] != firstCharBack){
                flag = false;
            }
        }
        // 判断是否为回形针字符
        return flag;
    }

    private static boolean isHuiX(String substring) {
        char[] rr = substring.toCharArray();
        if (rr.length%2 != 0){
            return false;
        }
        boolean flag = true;
        for (int i = 0; i < rr.length/2; i++) {
            if (rr[i]==')'||rr[i]=='}'||rr[i]==']'){
                flag = false;
                break;
            }
            if (rr[i] != getBack(rr[rr.length-1-i])){
                flag = false;
                break;
            }
        }
        return flag;
    }

    private static char getBack(char firstChar) {
        switch (firstChar){
            case '(':
                return ')';
            case ')':
                return '(';
            case '{':
                return '}';
            case '}':
                return '{';
            case '[':
                return ']';
            case ']':
                return '[';
            default:
                return 'n';
        }
    }
}
