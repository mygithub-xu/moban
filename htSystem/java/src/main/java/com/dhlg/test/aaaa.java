package com.dhlg.test;

import java.util.ArrayList;
import java.util.List;

public class aaaa {

    public static void main(String[] args) {
        List list1=new ArrayList();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        System.out.println("list1:"+list1);

        List list2=new ArrayList();

        list2=list1;//简单的直接赋值
        list2.set(1,"12");
        System.out.println("list1:"+list1);
        System.out.println("list2:"+list2);

    }
}
