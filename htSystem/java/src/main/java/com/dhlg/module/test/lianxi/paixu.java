package com.dhlg.module.test.lianxi;

import java.util.Arrays;

public class paixu {
    public static void main(String[] args) {
        // 冒泡
        int[] aa = {3,45,78,64,52,11,64,55,99,11,18};
//        maopao(aa);
        // 优化冒泡
//        maopaoyouhua(aa);
        // 快排
        kuaiPai(aa);
    }

    private static void kuaiPai(int[] aa) {

    }

    private static void maopaoyouhua(int[] aa) {
        for (int i = 0; i < aa.length -1; i++) {
            boolean flag = false;
            for (int j = 0; j < aa.length - i -1; j++) {
                if (aa[j]>aa[j+1]){
                    int b = aa[j];
                    aa[j] = aa[j+1];
                    aa[j+1] = b;
                    flag = true;
                }
            }
            if (!flag){
                break;
            }
        }
        System.out.println(Arrays.toString(aa));
    }

    private static void maopao(int[] aa) {
        for (int i = 0; i < aa.length -1; i++) {
            for (int j = 0; j < aa.length - i -1; j++) {
                if (aa[j]>aa[j+1]){
                    int b = aa[j];
                    aa[j] = aa[j+1];
                    aa[j+1] = b;
                }
            }
        }
        System.out.println(Arrays.toString(aa));
    }
}
