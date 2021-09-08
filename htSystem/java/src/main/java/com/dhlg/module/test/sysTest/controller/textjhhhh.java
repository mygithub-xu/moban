package com.dhlg.module.test.sysTest.controller;

import com.dhlg.utils.common.StringUtils;
import org.apache.velocity.runtime.directive.Foreach;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static jdk.nashorn.internal.objects.NativeString.search;

public class textjhhhh {
    public static void main(String[] args){
        // 接雨水 力扣42. 接雨水
        int[] height = {0,1,2};
        int aa = trap(height);

        // 面试题 16.26. 计算器
//        String s = " 3+5 / 2 ";
//        calculate(s);
//        // 150. 逆波兰表达式求值
//        String[] tokens = {"2","1","+","3","*"};
//        int sum = evalRPN(tokens);

//        // 复习二分查找
//        int[] nums = {5,7,7,8,8,10};
//        int target = 8;
//        int index = erfen(nums,target);
//        System.out.println(index);
        // 34. 在排序数组中查找元素的第一个和最后一个位置  (使用二分法)
//        int[] nums = {1};
//        int target = 1;
//        int index = searchA(nums, target);
////        int[] ints = searchRange(nums, target);
//        System.out.println(index);

        // 452 用最少数量的箭引爆气球
//        int[][] points  = {{0,9},{0,6},{2,9},{2,8},{3,9},{3,8},{3,9},{6,8},{7,12},{9,10}};
//        int[][] points  = {{1,2}, {3,4},{5,6},{7,8}};
//        int[][] points  = {{10,16}, {2,8},{1,6},{7,12}};
//        int count = findMinArrowShots(points);
//        System.out.println(count);

        // 455.分发饼干
//        int[] g = {1,2};
//        int[] s = {1,2,3};
//        System.out.println(findContentChildren(g,s));
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

    private static int trap(int[] height) {
        int returnNum = 0;
        // 左边面积
        int s1 = 0;
        // 右边面积
        int s2 = 0;
        // 柱子面积
        int s3 = 0;

        int maxLeft = 0;
        int maxRight = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i]>maxLeft){
                maxLeft = height[i];
            }
            if (height[height.length - i -1]>maxRight){
                maxRight = height[height.length - i -1];
            }
            s1 = s1 + maxLeft;
            s2 = s2 + maxRight;
            s3 = s3 + height[i];
        }
        // 矩形面积
        int s4 = height.length * maxLeft;
        returnNum = s1 + s2 - s3 - s4;
        System.out.println(returnNum);
        return returnNum;
    }

    private static int calculate(String s) {

        Stack<Integer> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0;i<chars.length;i++){
            char tmp = chars[i];
            if (tmp == ' '){
                continue;
            }
            int num = 0;
            if (tmp == '*' || tmp == '/' || tmp == '+' || tmp == '-'){
                i++;
                num = Character.getNumericValue(chars[i]);
            }else {
                num = Character.getNumericValue(tmp);
            }
            switch (tmp){
                case '-':
                    num = -num;
                    break;
                case '*':
                    num = stack.pop()*num;
                    break;
                case '/':
                    num = stack.pop()/num;
                    break;
            }
            stack.push(num);
        }
        int sum = 0;
        while(!stack.isEmpty()) {
            sum += stack.pop();
        }
        System.out.println(sum);
        return sum;
    }

    private static int evalRPN(String[] tokens) {
        int returnNum = 0;
        Stack<Integer> stack = new Stack<>();
        for(String token : tokens){
            if (isNumber(token)){
                stack.push(Integer.valueOf(token));
            }
            if (!isNumber(token)){
                int num1 = stack.pop();
                int num2 = stack.pop();
                switch (token){
                    case "-":
                        stack.push(num2 - num1);
                        break;
                    case "+":
                        stack.push(num2 + num1);
                        break;
                    case "*":
                        stack.push(num2 * num1);
                        break;
                    case "/":
                        stack.push(num2 / num1);
                        break;
                }
            }
        }
        Integer pop = stack.pop();
        System.out.println(pop);
        return pop;
    }

    private static boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }

    private static int erfen(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int middle = 0;			//定义middle

        if(key < arr[low] || key > arr[high] || low > high){
            return -1;
        }

        while(low <= high){
            middle = (low + high) / 2;
            if(arr[middle] > key){
                //比关键字大则关键字在左区域
                high = middle - 1;
            }else if(arr[middle] < key){
                //比关键字小则关键字在右区域
                low = middle + 1;
            }else{
                return middle;
            }
        }

        return -1;		//最后仍然没有找到，则返回-1
    }

    private static int searchA(int[] nums, int target) {
        int n = -1;
        for (int i = 0; i <nums.length; i++) {
            if (nums[i] == target){
                n = i;
                break;
            }
        }
        return n;
    }

    private static int[] searchRange(int[] nums, int target) {
        int[] result;
        if (nums.length == 0 || (nums.length == 1 && nums[0] != target)){
            return new int[]{-1,-1};
        }
        if (nums.length == 1){
            return new int[]{0,0};
        }
        int start = 0;
        int end = nums.length - 1;
        int index = -1;
        while (start <= end) {
            if (start == end -1){
                if (nums[start] != target && nums[end] != target){
                    start = -1;
                    end = -1;
                    break;
                }
                if (nums[start] == target){
                    break;
                }
                if (nums[end] == target){
                    start = end;
                    break;
                }
            }
            index = (start + end)/2;
            if (nums[index] == target){
                start = index;
                break;
            }
            if (nums[index] > target){
                end = index;
                continue;
            }
            if (nums[index] < target){
                start = index;
            }
        }

        if (start != -1){
            end = start;
            // 向左
            while (start != 0 && nums[start-1] == target){
                start--;
            }
            // 向右
            while (end != nums.length-1 &&nums[end+1] == target){
                end++;
            }
        }
        result = new int[]{start,end};
        return result;
    }

    private static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        System.out.println(Arrays.deepToString(points));
        int change = 1;
        int count = 0;
        for (int i = 0;i<points.length-1;i++){
            int maxNum = points[count][1];
            int CampNum = points[i+1][0];
            if (maxNum < CampNum){
                change++;
                count = i+1;
            }
        }
        return change;
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
