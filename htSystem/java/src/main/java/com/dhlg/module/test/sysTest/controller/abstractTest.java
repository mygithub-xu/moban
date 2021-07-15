package com.dhlg.module.test.sysTest.controller;

import org.junit.Test;

import java.math.BigDecimal;

public  class abstractTest {


    @Test
    public void aaaa(){
        int[] aa= {1,2,3};
        BigDecimal num = new BigDecimal(0);
        for (int i = 0; i < aa.length; i++) {
            // 拿到值
            BigDecimal i2 = BigDecimal.valueOf(aa[i]);
            num = i2.add(num);
        }

        System.out.println(num);
    }

}
