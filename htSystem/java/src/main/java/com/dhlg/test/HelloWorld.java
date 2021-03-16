package com.dhlg.test;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;


@WebService
public class HelloWorld {
    @WebMethod
    public String sayHello(String str){
        System.out.println("get Message...");
        String result = "Hello World, "+str;
        return result;
    }
    public static void main(String[] args) {
//        System.out.println("server is running");
//        String address="http://localhost:9000/HelloWorld";
//        Object implementor =new HelloWorld();
//        Endpoint.publish(address, implementor);
        int[] array = {1,3,2,4,5,7,6,8,9,12};
//        bubbleSort(array);
        selectionSort(array);
        daying(array);
    }
    /*
    * 冒泡算法
    */
    private static void bubbleSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i -1; j++) {
                if (array[j] > array[j+1]){
                    swap(array,j,j+1);
                }
            }
        }

    }

    private static void daying(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    /*
     *  选择排序
     */
    public static void selectionSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            // 保存最小数的索引
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                // 找到最小的数
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // 交换元素位置
            if (i != minIndex) {
                swap(array, minIndex, i);
            }
        }
    }
    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

}