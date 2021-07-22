package com.dhlg.module.test.sysTest.controller;

import com.dhlg.utils.common.StringUtils;
import org.apache.velocity.runtime.directive.Foreach;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class textjhhhh {
    public static void main(String[] args){
        // 455.分发饼干
        int[] g = {1,2};
        int[] s = {1,2,3};
        System.out.println(findContentChildren(g,s));
//        int[] nums = {1,1,1,2,2,3};
//        int k = 2;
//        printArray(topKFrequent(nums, k));

//        String s = "{}{}{}{}{}{(([[]]))]";
//        boolean valid = isValid(s);

//        String s = "a1b2c3d4e";
//        String valid = replaceDigits(s);
//        System.out.println(valid);

//        int[] numbers = {2,7,11,15};
//        int target = 9;
//        int[] index = twoSum(numbers,target);
//        System.out.println(Arrays.toString(index));

        // 优化版冒泡算法
//        int[] numbers = {-1,0,4,6,7,1,2,0,7};
//        int[] index = maoPao(numbers);
//        System.out.println(Arrays.toString(index));

//        int[] numbers = {3,2,3,1,2,4,5,5,6};
//        int index = findKthLargest(numbers,4);
//        System.out.println(index);

        // 递归file
//        File file = new File("D:/aaaaaa");
//        print(file);

        //给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
//        int num = 1;
//        System.out.println(judgeSquareSum(num));

    }

    private static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        // 满足的人数
        int gi = 0;
        // 饼干的数量
        int si = 0;
        while (gi<g.length&&si<s.length){
            if (g[gi]<=s[si]){
                gi++;
            }
            si++;
        }
        return gi;
    }

    private  static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> stringIntegerHashMap = new HashMap<>();
        for(int a : nums){
            //查找是否存在该值
            Integer count = stringIntegerHashMap.get(a);
            if (count==null){
                count = 0;
            }
            count++;
            stringIntegerHashMap.put(a,count);
        }
        ArrayList<Integer> integers = new ArrayList<>();
        // map值排序
        for (int a: stringIntegerHashMap.keySet()){
            integers.add(stringIntegerHashMap.get(a));
        }
        int[] num1 = new int[integers.size()];
        for (int i = 0; i < integers.size(); i++) {
            num1[i] = integers.get(i);
        }
        Arrays.sort(num1);
        int count = 0;
        if (num1.length-k-1 >= 0){
            count = num1[num1.length-k-1];
        }
        int[] num2 = new int[k];
        int n = 0;
        for (int a: stringIntegerHashMap.keySet()){
            if (stringIntegerHashMap.get(a)>count){
                System.out.println(stringIntegerHashMap.get(a));
                num2[n] = a;
                n++;
            }
        }
        return num2;
    }

    private static boolean judgeSquareSum(int c) {
        int font = 0;
        int behind = (int)Math.sqrt(c);
        boolean flag = false;
        while (font <= behind){
            int sum = font*font+behind*behind;
            if (sum == c){
                flag = true;
                break;
            }
            if (sum > c){
                behind--;
            }else {
                font++;
            }
        }
        return flag;
    }

    private static void print(File file) {
        System.out.println(file.getName());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                print(f);
            }
        }
    }

    private static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        return nums[nums.length-k];
    }
    private static int[] maoPao(int[] numbers) {
        for (int i = 0;i<numbers.length-1;i++){
            boolean hasSwitch = false;
            for (int j = 0;j<numbers.length-i-1;j++){
                if (numbers[j]>numbers[j+1]){
                    int a = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = a;
                    hasSwitch = true;
                }
            }
            System.out.println("第"+ i +"遍最终结果");
            printArray(numbers);
            if (!hasSwitch){
                break;
            }
        }
        return numbers;
    }
    private static void printArray(int[] array){
        for(int i : array) {
            System.out.print(i + " ");
        }
    }

    private static int[] twoSum(int[] numbers, int target) {
//        int[] aa = new int[2];
//        for (int i = 0;i<numbers.length-1;i++){
//            for (int j = i+1;j<numbers.length;j++){
//                if (numbers[i] + numbers[j] == target){
//                    aa[0] = i+1;
//                    aa[1] = j+1;
//                    break;
//                }
//            }
//        }
//        return aa;

        int[] aa = new int[2];
        // 指针i，移向大的元素使num值变大
        int i = 0;
        // 指针j，移向小的元素使num值变小
        int j = numbers.length-1;
        while (i < j) {
            int num = numbers[i] + numbers[j];
            if (num == target) {
                aa[0] = i+1;
                aa[1] = j+1;
                break;
            }
            if (num > target) {
                j--;
            } else {
                i++;
            }
        }
        return aa;
    }

    private static String replaceDigits(String s) {
        char[] aa = s.toCharArray();
        for (int i = 0; i < aa.length;i++) {
            if (i%2 == 0&& aa.length!=i+1){
                aa[i+1] = shift(aa[i], String.valueOf(aa[i+1]));
            }
        }
        return String.valueOf(aa);
    }

    private static char shift(char c, String c1) {
        return (char)(c+Integer.parseInt(c1));
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
