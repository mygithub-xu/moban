package com.dhlg.module.test.lianxi.designPattern.singletonMode;

public  class Ehan {
    // 饿汉
    private Ehan(){

    }
    private static Ehan ehan = new Ehan();

    public static Ehan getEhan() {
        return ehan;
    }
}
