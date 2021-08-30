package com.dhlg.module.test.lianxi;

public  class Ehan {
    // 饿汉
    private Ehan(){

    }
    private static Ehan ehan = new Ehan();

    public static Ehan getEhan() {
        return ehan;
    }
}
