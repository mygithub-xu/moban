package com.dhlg.module.test.lianxi.designPattern.factoryMode;

/*
* 简单工厂模式
*/
public class Factory {
    public static void main(String[] args) {
        ProductFactory productFactory = new ProductFactory();
        Product a = productFactory.getProductVO("A");
        a.get();
    }
}
class ProductFactory{
    Product getProductVO(String s){
        if ("A".equals(s)){
            return new ProductA();
        }
        if ("B".equals(s)){
            return new ProductB();
        }
        return null;
    }
}
interface Product{
    public void get();
}
class ProductA implements Product{
    @Override
    public void get(){
        System.out.println("productA");
    }
}
class ProductB implements Product{
    @Override
    public void get(){
        System.out.println("productB");
    }
}
