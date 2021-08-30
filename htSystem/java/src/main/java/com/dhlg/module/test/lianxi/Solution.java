package com.dhlg.module.test.lianxi;

import java.util.LinkedList;



public class Solution {

    public static void main(String[] args) {
        int n = 10;
        int aa = getStep(n);
        System.out.println(aa);
    }

    private static int getStep(int n) {
        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }
        return getStep(n-1) + getStep(n-2);
    }
}
